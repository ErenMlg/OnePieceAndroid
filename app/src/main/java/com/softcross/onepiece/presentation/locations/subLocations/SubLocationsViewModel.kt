package com.softcross.onepiece.presentation.locations.subLocations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.common.extension.mapExtensions.toSubLocation
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.domain.GetAllSubLocationsByLocationUseCase
import com.softcross.onepiece.presentation.occupations.OccupationUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubLocationsViewModel @Inject constructor(
    private val getAllSubLocationsByLocationUseCase: GetAllSubLocationsByLocationUseCase,
    private val onePieceRepository: OnePieceRepository
) : ViewModel() {

    private val _subLocationsUiState = MutableLiveData<SubLocationsUiState>()
    val subLocationsUiState: LiveData<SubLocationsUiState> get() = _subLocationsUiState

    fun getAllSubLocationsByLocation(locationID: String) {
        viewModelScope.launch {
            getAllSubLocationsByLocationUseCase(locationID).collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> _subLocationsUiState.postValue(SubLocationsUiState.Loading)
                    is ResponseState.Error -> _subLocationsUiState.postValue(
                        SubLocationsUiState.Error(
                            responseState.message
                        )
                    )

                    is ResponseState.Success -> _subLocationsUiState.postValue(
                        SubLocationsUiState.Success(
                            responseState.data.map {
                                val isFavorite = onePieceRepository.isFavoriteSubLocation(it.id)
                                SubLocationUiItem(
                                    id = it.id,
                                    subLocationName = it.subLocationName,
                                    subLocationPictureURL = it.subLocationPictureURL,
                                    firstAppearance = it.firstAppearance,
                                    locationName = it.locationName,
                                    isFavorite = isFavorite
                                )
                            }
                        )
                    )
                }

            }
        }
    }

    fun changeSubLocationFavoriteState(subLocation: SubLocationUiItem) {
        viewModelScope.launch {
            if (subLocation.isFavorite) {
                onePieceRepository.deleteFavoriteSubLocation(subLocation.toSubLocation())
                subLocation.isFavorite = false
            } else {
                onePieceRepository.addFavoriteSubLocation(subLocation.toSubLocation())
                subLocation.isFavorite = true
            }
        }
    }
}

sealed class SubLocationsUiState {
    object Loading : SubLocationsUiState()
    data class Error(val errorMessage: String) : SubLocationsUiState()
    data class Success(val data: List<SubLocationUiItem>) : SubLocationsUiState()
}

data class SubLocationUiItem(
    val id: String,
    val subLocationName: String,
    val subLocationPictureURL: String,
    val firstAppearance: String,
    val locationName: String,
    var isFavorite: Boolean
)