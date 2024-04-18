package com.softcross.onepiece.presentation.characters.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.domain.GetSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val onePieceRepository: OnePieceRepository
) : ViewModel() {

    private val _characterDetailScreenUiState = MutableLiveData<CharacterDetailUiState>()
    val characterDetailScreenUiState: LiveData<CharacterDetailUiState> get() = _characterDetailScreenUiState

    fun getCharacter(uuid: String) {
        viewModelScope.launch {
            combine(
                getSingleCharacterUseCase(uuid),
                onePieceRepository.getAllFavoriteCharacters()
            ) { singleCharacterResponseState, favoriteCharacters ->
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
                        val isFavorite =
                            favoriteCharacters.any { it.id == singleCharacterResponseState.data.id }
                        _characterDetailScreenUiState.postValue(
                            CharacterDetailUiState.Success(singleCharacterResponseState.data, isFavorite)
                        )
                    }
                }
            }.collect()
        }
    }

    fun changeCharacterFavoriteState(character: Character, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                onePieceRepository.deleteFavoriteCharacter(character)
            } else {
                onePieceRepository.addFavoriteCharacter(character)
            }
        }
    }

}

sealed class CharacterDetailUiState {
    object Loading : CharacterDetailUiState()
    data class Error(val errorMessage: String) : CharacterDetailUiState()
    data class Success(val data: Character, val isFavorite: Boolean) : CharacterDetailUiState()
}