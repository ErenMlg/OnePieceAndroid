package com.softcross.onepiece.core.network.source.rest

import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(private val onePieceRestApi: OnePieceRestApi):RestDataSource {
    override suspend fun getAllCharacters(): Response<CharacterResponse> {
        return onePieceRestApi.getAllCharacters()
    }

    override suspend fun getSingleCharacter(id: String): Response<CharacterDto> {
        return onePieceRestApi.getSingleCharacters(id)
    }
}