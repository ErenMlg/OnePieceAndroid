package com.softcross.onepiece.core.domain

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDevilFruitsUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {

    suspend operator fun invoke(): Flow<ResponseState<List<DevilFruit>>> {
        return onePieceRepository.getAllDevilFruits()
    }

}