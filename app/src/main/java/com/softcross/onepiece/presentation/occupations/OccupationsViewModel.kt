package com.softcross.onepiece.presentation.occupations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.domain.GetAllOccupationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OccupationsViewModel @Inject constructor(
    private val getAllOccupationsUseCase: GetAllOccupationsUseCase
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
                                responseState.data
                            )
                        )
                    }
                }
            }
        }
    }

}

sealed class OccupationsUiState {
    object Loading : OccupationsUiState()
    data class Error(val errorMessage: String) : OccupationsUiState()
    data class Success(val occupationList: List<Occupations>) : OccupationsUiState()
}