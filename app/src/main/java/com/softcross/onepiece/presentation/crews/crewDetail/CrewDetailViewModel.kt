package com.softcross.onepiece.presentation.crews.crewDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.domain.GetSingleCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewDetailViewModel @Inject constructor(
    private val getSingleCrewUseCase: GetSingleCrewUseCase,
    private val onePieceRepository: OnePieceRepository
) : ViewModel() {

    private val _crewDetailUiState = MutableLiveData<CrewDetailUiState>()
    val crewDetailUiState: LiveData<CrewDetailUiState> get() = _crewDetailUiState

    fun getSingleCrew(id: String) {
        viewModelScope.launch {
            combine(
                getSingleCrewUseCase(id),
                onePieceRepository.getAllFavoriteCrews()
            ) { responseState, favoriteCrews ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _crewDetailUiState.postValue(CrewDetailUiState.Loading)
                    }

                    is ResponseState.Error -> {
                        _crewDetailUiState.postValue(CrewDetailUiState.Error(responseState.message))
                    }

                    is ResponseState.Success -> {
                        val isFavorite = favoriteCrews.any { it.id == responseState.data.id }
                        _crewDetailUiState.postValue(
                            CrewDetailUiState.Success(
                                responseState.data,
                                isFavorite
                            )
                        )
                    }
                }
            }.collect()
        }
    }

    fun changeCrewFavoriteState(crew: Crew, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                onePieceRepository.deleteFavoriteCrew(crew.id)
            } else {
                onePieceRepository.addFavoriteCrew(crew)
            }
        }
    }
}

sealed class CrewDetailUiState {
    object Loading : CrewDetailUiState()
    data class Error(val errorMessage: String) : CrewDetailUiState()
    data class Success(val data: Crew, val isFavorite: Boolean) : CrewDetailUiState()
}
