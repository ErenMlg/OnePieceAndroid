package com.softcross.onepiece.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.repository.OnePieceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VM @Inject constructor(private val onePieceRepositoryImpl: OnePieceRepositoryImpl) :
    ViewModel() {

    private val _characterScreenUiState = MutableLiveData(CharacterListScreenUiState.initial())
    val characterScreenUiState: LiveData<CharacterListScreenUiState> get() = _characterScreenUiState

    fun getAll() {
        viewModelScope.launch {
            onePieceRepositoryImpl.getAllCharacters().collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val characters = responseState.data
                        _characterScreenUiState.postValue(CharacterListScreenUiState(characters))
                    }

                    is ResponseState.Loading -> {
                        _characterScreenUiState.postValue(CharacterListScreenUiState(isLoading = true))
                    }

                    is ResponseState.Error -> {
                        _characterScreenUiState.postValue(
                            CharacterListScreenUiState(
                                isError = true,
                                errorMessage = responseState.message
                            )
                        )
                    }
                }
            }
        }
    }

}

data class CharacterListScreenUiState(
    val character: List<CharacterEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
) {
    companion object {
        fun initial() = CharacterListScreenUiState(isLoading = true)
    }
}