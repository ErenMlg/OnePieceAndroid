package com.softcross.onepiece.presentation.characters.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.domain.GetSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase
) : ViewModel() {

    private val _characterDetailScreenUiState = MutableLiveData<CharacterDetailUiState>()
    val characterDetailScreenUiState: LiveData<CharacterDetailUiState> get() = _characterDetailScreenUiState

    fun getCharacter(uuid: String) {
        viewModelScope.launch {
            getSingleCharacterUseCase(uuid).collect { singleCharacterResponseState ->
                when (singleCharacterResponseState) {
                    is ResponseState.Loading -> {
                        _characterDetailScreenUiState.postValue(CharacterDetailUiState.Loading)
                    }

                    is ResponseState.Error -> {
                        _characterDetailScreenUiState.postValue(
                            CharacterDetailUiState.Error(
                                singleCharacterResponseState.message
                            )
                        )
                    }

                    is ResponseState.Success -> {
                        _characterDetailScreenUiState.postValue(
                            CharacterDetailUiState.Success(singleCharacterResponseState.data)
                        )
                    }
                }
            }
        }
    }
}

sealed class CharacterDetailUiState {
    object Loading : CharacterDetailUiState()
    data class Error(val errorMessage: String) : CharacterDetailUiState()
    data class Success(val data: Character) : CharacterDetailUiState()
}