package com.softcross.onepiece.presentation.crews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.domain.GetAllCrewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewsViewModel @Inject constructor(private val getAllCrewsUseCase: GetAllCrewsUseCase) :
    ViewModel() {

    private val _crewsUiState = MutableLiveData<CrewListUiState>()
    val crewsUiState: LiveData<CrewListUiState> get() = _crewsUiState


    fun getAllCrews() {
        viewModelScope.launch {
            getAllCrewsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _crewsUiState.postValue(CrewListUiState.Loading)
                    }

                    is ResponseState.Error -> {
                        _crewsUiState.postValue(CrewListUiState.Error(responseState.message))
                    }

                    is ResponseState.Success -> {
                        val crewList = responseState.data.map {
                            CrewUiItem(
                                it.id,
                                it.crewFlagURL,
                                it.crewName
                            )
                        }
                        _crewsUiState.postValue(CrewListUiState.Success(crewList))
                    }
                }

            }
        }
    }
}

sealed class CrewListUiState {
    object Loading : CrewListUiState()
    data class Error(val errorMessage: String) : CrewListUiState()
    data class Success(val data: List<CrewUiItem>) : CrewListUiState()
}

data class CrewUiItem(
    val id: String,
    val crewImage: String,
    val crewName: String
)