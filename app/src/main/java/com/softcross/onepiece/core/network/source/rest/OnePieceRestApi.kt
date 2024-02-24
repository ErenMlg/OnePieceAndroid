package com.softcross.onepiece.core.network.source.rest

import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface OnePieceRestApi {

    @GET("characters")
    suspend fun getAllCharacters(): Response<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getSingleCharacters(@Path("id") id:String): Response<CharacterDto>

}