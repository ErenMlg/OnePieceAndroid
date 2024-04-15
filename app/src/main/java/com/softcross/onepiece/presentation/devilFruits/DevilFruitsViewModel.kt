package com.softcross.onepiece.presentation.devilFruits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.domain.GetAllDevilFruitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevilFruitsViewModel @Inject constructor(private val getAllDevilFruitsUseCase: GetAllDevilFruitsUseCase) :
    ViewModel() {

    private val _devilFruitsUiState = MutableLiveData<DevilFruitUiState>()
    val devilFruitsUiState: LiveData<DevilFruitUiState> get() = _devilFruitsUiState

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
                        _devilFruitsUiState.postValue(DevilFruitUiState.Success(responseState.data))
                    }
                }

            }
        }
    }

}

sealed class DevilFruitUiState {
    object Loading : DevilFruitUiState()
    data class Error(val errorMessage: String) : DevilFruitUiState()
    data class Success(val data: List<DevilFruit>) : DevilFruitUiState()
}