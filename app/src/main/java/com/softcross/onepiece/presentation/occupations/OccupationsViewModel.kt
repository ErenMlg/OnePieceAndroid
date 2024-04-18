package com.softcross.onepiece.presentation.occupations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.common.extension.mapExtensions.toOccupation
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.domain.GetAllOccupationsUseCase
import com.softcross.onepiece.presentation.devilFruits.DevilFruitUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OccupationsViewModel @Inject constructor(
    private val getAllOccupationsUseCase: GetAllOccupationsUseCase,
    private val onePieceRepository: OnePieceRepository
) : ViewModel() {

    private val _occupationUiState = MutableLiveData<OccupationsUiState>()
    val occupationUiState: LiveData<OccupationsUiState> get() = _occupationUiState

    fun getALlOccupations() {
        viewModelScope.launch {
            getAllOccupationsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> _occupationUiState.postValue(OccupationsUiState.Loading)
                    is ResponseState.Error -> _occupationUiState.postValue(
                        OccupationsUiState.Error(
                            responseState.message
                        )
                    )

                    is ResponseState.Success -> {
                        _occupationUiState.postValue(

                            OccupationsUiState.Success(
                                responseState.data.map {
                                    val isFavorite = onePieceRepository.isFavoriteOccupation(it.id)
                                    OccupationUiItem(
                                        id = it.id,
                                        occupationName = it.occupationName,
                                        occupationDesc = it.occupationDesc,
                                        occupationPictureURL = it.occupationPictureURL,
                                        isFavorite = isFavorite
                                    )
                                }
                            )
                        )
                    }
                }
            }
        }
    }


    fun changeOccupationFavoriteState(occupations: OccupationUiItem) {
        viewModelScope.launch {
            if (occupations.isFavorite) {
                onePieceRepository.deleteFavoriteOccupation(occupations.id)
                occupations.isFavorite = false
            } else {
                onePieceRepository.addFavoriteOccupation(occupations.toOccupation())
                occupations.isFavorite = true
            }
        }
    }

}

sealed class OccupationsUiState {
    object Loading : OccupationsUiState()
    data class Error(val errorMessage: String) : OccupationsUiState()
    data class Success(val occupationList: List<OccupationUiItem>) : OccupationsUiState()
}

data class OccupationUiItem(
    val id: String,
    val occupationName: String,
    val occupationDesc: String,
    val occupationPictureURL: String,
    var isFavorite: Boolean
)