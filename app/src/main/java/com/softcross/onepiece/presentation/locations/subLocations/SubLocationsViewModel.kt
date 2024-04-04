package com.softcross.onepiece.presentation.locations.subLocations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import com.softcross.onepiece.core.domain.usecase.GetAllSubLocationsByLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubLocationsViewModel @Inject constructor(
    private val getAllSubLocationsByLocationUseCase: GetAllSubLocationsByLocationUseCase
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
                            responseState.data
                        )
                    )
                }

            }
        }
    }

}

sealed class SubLocationsUiState {
    object Loading : SubLocationsUiState()
    data class Error(val errorMessage: String) : SubLocationsUiState()
    data class Success(val data: List<SubLocationEntity>) : SubLocationsUiState()
}