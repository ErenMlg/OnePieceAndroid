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

interface RestDataSource {

    // Interface used because the app can seamlessly switch between the local and remote data
    // sources without affecting the business logic, and we can want write tests for our repository.
    // Also this provide us to readable codes

    suspend fun getAllCharacters(): Response<CharacterResponse>

    suspend fun getSingleCharacter(id: String): Response<CharacterDto>

    suspend fun getAllCrews(): Response<CrewResponse>

    suspend fun getSingleCrew(id: String): Response<CrewDto>

    suspend fun getALlDevilFruits(): Response<DevilFruitResponse>

    suspend fun getALlOccupations(): Response<OccupationResponse>

    suspend fun getAllLocations(): Response<LocationResponse>

    suspend fun getAllSubLocationsByLocation(locationID: String): Response<SubLocationResponse>

}