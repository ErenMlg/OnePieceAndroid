package com.softcross.onepiece.core.domain

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSubLocationsByLocationUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {

    suspend operator fun invoke(locationID: String): Flow<ResponseState<List<SubLocation>>> {
        return onePieceRepository.getAllSubLocationsByLocation(locationID)
    }

}