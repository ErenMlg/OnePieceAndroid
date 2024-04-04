package com.softcross.onepiece.core.domain.usecase

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.OccupationsEntity
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllOccupationsUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {

    suspend operator fun invoke(): Flow<ResponseState<List<OccupationsEntity>>> {
        return onePieceRepository.getAllOccupations()
    }

}