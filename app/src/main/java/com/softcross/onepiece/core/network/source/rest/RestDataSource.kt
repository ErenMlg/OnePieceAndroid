package com.softcross.onepiece.core.network.source.rest

import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response

interface RestDataSource {

    // Interface used because the app can seamlessly switch between the local and remote data
    // sources without affecting the business logic, and we can want write tests for our repository.
    // Also this provide us to readable codes

    suspend fun getAllCharacters(): Response<CharacterResponse>

    suspend fun getSingleCharacter(id: String): Response<CharacterDto>

}