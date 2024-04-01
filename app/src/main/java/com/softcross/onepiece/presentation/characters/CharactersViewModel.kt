package com.softcross.onepiece.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {

    private val _characterScreenUiState = MutableLiveData(CharacterListScreenUiState.initial())
    val characterScreenUiState: LiveData<CharacterListScreenUiState> get() = _characterScreenUiState

    fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase().collect { responseState ->
                when (responseState) {
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

                    is ResponseState.Success -> {
                        val uiItems = mutableListOf<CharacterListItem>(CharacterListItem.VideoItem)
                        val uiCharacters = responseState.data.map { characterEntity ->
                            CharacterListUiItem(
                                characterEntity.id,
                                characterEntity.characterName,
                                characterEntity.characterBounty,
                                characterEntity.characterPictureURL
                            )
                        }
                        uiCharacters.forEach { uiCharacter ->
                            uiItems.add(CharacterListItem.CharacterItem(uiCharacter))
                        }
                        _characterScreenUiState.postValue(CharacterListScreenUiState(uiItems))
                    }
                }
            }
        }
    }

}

data class CharacterListScreenUiState(
    val uiItems: List<CharacterListItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
) {
    companion object {
        fun initial() = CharacterListScreenUiState(isLoading = true)
    }
}