package com.softcross.onepiece.presentation.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.presentation.characters.characterDetail.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val onePieceRepository: OnePieceRepository
) : ViewModel() {

    private val _favoritesScreenUiState =
        MutableLiveData<FavoritesUiState>()
    val favoritesScreenUiState: LiveData<FavoritesUiState> get() = _favoritesScreenUiState

    init {
        getAllFavorites()
    }

    fun getAllFavorites() {
        viewModelScope.launch {
            combine(
                onePieceRepository.getAllFavoriteCharacters(),
                onePieceRepository.getAllFavoriteCrews(),
                onePieceRepository.getAllFavoriteDevilFruits(),
                onePieceRepository.getAllFavoriteOccupations(),
                onePieceRepository.getAllFavoriteSubLocations()
            ) { characters, crews, devilFruits, occupations, subLocations ->
                val favoriteList = mutableListOf<FavoritesUiItem>()
                favoriteList.addAll(characters.map {
                    FavoritesUiItem(
                        it.id,
                        it.characterName,
                        it.characterPictureURL,
                        "Character"
                    )
                })
                favoriteList.addAll(crews.map {
                    FavoritesUiItem(
                        it.id,
                        it.crewName,
                        it.crewFlagURL,
                        "Crew"
                    )
                })
                favoriteList.addAll(devilFruits.map {
                    FavoritesUiItem(
                        it.id,
                        it.devilFruitName,
                        it.devilFruitPictureURL,
                        "DevilFruit"
                    )
                })
                favoriteList.addAll(occupations.map {
                    FavoritesUiItem(
                        it.id,
                        it.occupationName,
                        it.occupationPictureURL,
                        "Occupations"
                    )
                })
                favoriteList.addAll(subLocations.map {
                    FavoritesUiItem(
                        it.id,
                        it.subLocationName,
                        it.subLocationPictureURL,
                        "SubLocations"
                    )
                })
                if (favoriteList.isNotEmpty()) {
                    _favoritesScreenUiState.postValue(FavoritesUiState.Success(favoriteList))
                } else {
                    _favoritesScreenUiState.postValue(FavoritesUiState.Error("Favorites not found!"))
                }
            }.collect()
        }
    }

    fun deleteFavoriteItem(favoritesUiItem: FavoritesUiItem) {
        viewModelScope.launch {
            when (favoritesUiItem.type) {
                "Character" -> {
                    onePieceRepository.deleteFavoriteCharacter(favoritesUiItem.id)
                }

                "Crew" -> {
                    onePieceRepository.deleteFavoriteCrew(favoritesUiItem.id)
                }

                "DevilFruit" -> {
                    onePieceRepository.deleteFavoriteDevilFruit(favoritesUiItem.id)
                }

                "Occupations" -> {
                    onePieceRepository.deleteFavoriteOccupation(favoritesUiItem.id)
                }

                "SubLocations" -> {
                    onePieceRepository.deleteFavoriteSubLocation(favoritesUiItem.id)
                }
            }
        }
    }

}

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()
    data class Error(val errorMessage: String) : FavoritesUiState()
    data class Success(val data: List<FavoritesUiItem>) : FavoritesUiState()
}

data class FavoritesUiItem(
    val id: String,
    val name: String,
    val imageURL: String,
    val type: String
)