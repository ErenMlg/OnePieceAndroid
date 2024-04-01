package com.softcross.onepiece.presentation.characters.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.domain.usecase.GetSingleCharacterUseCase
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
                            CharacterDetailUiState.Success(
                                CharacterDetailUiItem(
                                    singleCharacterResponseState.data.characterName,
                                    singleCharacterResponseState.data.characterPictureURL,
                                    singleCharacterResponseState.data.characterStatus,
                                    singleCharacterResponseState.data.characterCrew,
                                    if (singleCharacterResponseState.data.characterDevilFruit == "") "None" else singleCharacterResponseState.data.characterDevilFruit,
                                    singleCharacterResponseState.data.characterOrigin,
                                    singleCharacterResponseState.data.characterOccupation,
                                    singleCharacterResponseState.data.characterAbilities,
                                    singleCharacterResponseState.data.characterBounty
                                )
                            )
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
    data class Success(val data: CharacterDetailUiItem) : CharacterDetailUiState()
}

data class CharacterDetailUiItem(
    val name: String,
    val picture: String,
    val status: String,
    val crew: String,
    val devilFruit: String,
    val origin: String,
    val occupation: String,
    val abilities: String,
    val bounty: String
)