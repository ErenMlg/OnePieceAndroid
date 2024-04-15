package com.softcross.onepiece.core.domain

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val onePieceRepository: OnePieceRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<Character>>> {
        return onePieceRepository.getAllCharacters()
    }
}