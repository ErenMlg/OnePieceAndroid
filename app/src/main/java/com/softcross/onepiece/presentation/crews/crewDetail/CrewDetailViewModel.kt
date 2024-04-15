package com.softcross.onepiece.presentation.crews.crewDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.domain.GetSingleCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewDetailViewModel @Inject constructor(
    private val getSingleCrewUseCase: GetSingleCrewUseCase
) : ViewModel() {

    private val _crewDetailUiState = MutableLiveData<CrewDetailUiState>()
    val crewDetailUiState: LiveData<CrewDetailUiState> get() = _crewDetailUiState

    fun getSingleCrew(id: String) {
        viewModelScope.launch {
            getSingleCrewUseCase(id).collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _crewDetailUiState.postValue(CrewDetailUiState.Loading)
                    }

                    is ResponseState.Error -> {
                        _crewDetailUiState.postValue(CrewDetailUiState.Error(responseState.message))
                    }

                    is ResponseState.Success -> {
                        _crewDetailUiState.postValue(CrewDetailUiState.Success(responseState.data))
                    }
                }
            }
        }
    }
}

sealed class CrewDetailUiState {
    object Loading : CrewDetailUiState()
    data class Error(val errorMessage: String = "") : CrewDetailUiState()
    data class Success(val data: Crew) : CrewDetailUiState()
}

data class CrewDetailUiItem(
    val id: String,
    val crewName: String,
    val crewTotalBounty: String,
    val crewMainShip: String,
    val crewFlagURL: String

)