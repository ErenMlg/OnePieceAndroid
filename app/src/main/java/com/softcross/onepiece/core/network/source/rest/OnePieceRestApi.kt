package com.softcross.onepiece.core.network.source.rest

import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import com.softcross.onepiece.core.network.dto.crew.CrewDto
import com.softcross.onepiece.core.network.dto.crew.CrewResponse
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitResponse
import com.softcross.onepiece.core.network.dto.location.LocationResponse
import com.softcross.onepiece.core.network.dto.location.sublocation.SubLocationResponse
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OnePieceRestApi {

    @GET("characters")
    suspend fun getAllCharacters(): Response<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getSingleCharacters(@Path("id") id: String): Response<CharacterDto>

    @GET("crews")
    suspend fun getAllCrews(): Response<CrewResponse>

    @GET("crews/{id}")
    suspend fun getSingleCrew(@Path("id") id: String): Response<CrewDto>

    @GET("devilFruits")
    suspend fun getAllDevilFruits(): Response<DevilFruitResponse>

    @GET("occupations")
    suspend fun getAllOccupations(): Response<OccupationResponse>

    @GET("locations")
    suspend fun getAllLocations(): Response<LocationResponse>

    @GET("/sublocations/subLocationByLocation/{locationID}")
    suspend fun getAllSubLocations(@Path("locationID") locationID: String): Response<SubLocationResponse>
}