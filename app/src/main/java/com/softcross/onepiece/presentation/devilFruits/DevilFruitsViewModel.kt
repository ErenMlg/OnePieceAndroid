package com.softcross.onepiece.presentation.devilFruits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.common.extension.mapExtensions.toDevilFruit
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.domain.GetAllDevilFruitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevilFruitsViewModel @Inject constructor(
    private val getAllDevilFruitsUseCase: GetAllDevilFruitsUseCase,
    private val onePieceRepository: OnePieceRepository
) :
    ViewModel() {

    private val _devilFruitsUiState = MutableLiveData<DevilFruitUiState>()
    val devilFruitsUiState: LiveData<DevilFruitUiState> get() = _devilFruitsUiState

    init {
        getAllDevilFruits()
    }

    fun getAllDevilFruits() {
        viewModelScope.launch {
            getAllDevilFruitsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _devilFruitsUiState.postValue(DevilFruitUiState.Loading)
                    }

                    is ResponseState.Error -> {
                        _devilFruitsUiState.postValue(DevilFruitUiState.Error(responseState.message))
                    }

                    is ResponseState.Success -> {
                        _devilFruitsUiState.postValue(
                            DevilFruitUiState.Success(responseState.data.map {
                                val isFav = onePieceRepository.isFavoriteDevilFruit(it.id)
                                DevilFruitUiItem(
                                    id = it.id,
                                    devilFruitName = it.devilFruitName,
                                    devilFruitType = it.devilFruitType,
                                    devilFruitPictureURL = it.devilFruitPictureURL,
                                    isFavorite = isFav
                                )
                            })
                        )
                    }
                }
            }
        }
    }


    fun changeDevilFruitFavoriteState(devilFruit: DevilFruitUiItem) {
        viewModelScope.launch {
            if (devilFruit.isFavorite) {
                onePieceRepository.deleteFavoriteDevilFruit(devilFruit.toDevilFruit())
                devilFruit.isFavorite = false
            } else {
                onePieceRepository.addFavoriteDevilFruit(devilFruit.toDevilFruit())
                devilFruit.isFavorite = true
            }
        }
    }

}

sealed class DevilFruitUiState {
    object Loading : DevilFruitUiState()
    data class Error(val errorMessage: String) : DevilFruitUiState()
    data class Success(val data: List<DevilFruitUiItem>) : DevilFruitUiState()
}

data class DevilFruitUiItem(
    val id: String,
    val devilFruitName: String,
    val devilFruitType: String,
    val devilFruitPictureURL: String,
    var isFavorite: Boolean
)