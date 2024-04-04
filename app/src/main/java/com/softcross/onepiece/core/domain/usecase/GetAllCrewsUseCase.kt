package com.softcross.onepiece.core.domain.usecase

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCrewsUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<CrewEntity>>> {
        return onePieceRepository.getAllCrews()
    }
}