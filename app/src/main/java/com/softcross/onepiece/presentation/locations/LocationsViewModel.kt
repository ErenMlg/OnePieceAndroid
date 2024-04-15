package com.softcross.onepiece.presentation.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.domain.GetAllLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val getAllLocationsUseCase: GetAllLocationsUseCase) :
    ViewModel() {

    private val _locationsUiState = MutableLiveData<LocationsUiState>()
    val locationsUiState: LiveData<LocationsUiState> get() = _locationsUiState

    fun getAllLocations() {
        viewModelScope.launch {
            getAllLocationsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> _locationsUiState.postValue(LocationsUiState.Loading)
                    is ResponseState.Error -> _locationsUiState.postValue(
                        LocationsUiState.Error(
                            responseState.message
                        )
                    )

                    is ResponseState.Success -> _locationsUiState.postValue(
                        LocationsUiState.Success(
                            responseState.data
                        )
                    )
                }

            }
        }
    }
}

sealed class LocationsUiState {
    object Loading : LocationsUiState()
    data class Error(val errorMessage: String) : LocationsUiState()
    data class Success(val data: List<Location>) : LocationsUiState()
}