package com.softcross.onepiece.core.domain

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocationsUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {

    suspend operator fun invoke(): Flow<ResponseState<List<Location>>> {
        return onePieceRepository.getAllLocations()
    }

}