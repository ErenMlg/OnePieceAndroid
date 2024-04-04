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
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(private val onePieceRestApi: OnePieceRestApi) :
    RestDataSource {
    override suspend fun getAllCharacters(): Response<CharacterResponse> {
        return onePieceRestApi.getAllCharacters()
    }

    override suspend fun getSingleCharacter(id: String): Response<CharacterDto> {
        return onePieceRestApi.getSingleCharacters(id)
    }

    override suspend fun getAllCrews(): Response<CrewResponse> {
        return onePieceRestApi.getAllCrews()
    }

    override suspend fun getSingleCrew(id: String): Response<CrewDto> {
        return onePieceRestApi.getSingleCrew(id)
    }

    override suspend fun getALlDevilFruits(): Response<DevilFruitResponse> {
        return onePieceRestApi.getAllDevilFruits()
    }

    override suspend fun getALlOccupations(): Response<OccupationResponse> {
        return onePieceRestApi.getAllOccupations()
    }

    override suspend fun getAllLocations(): Response<LocationResponse> {
        return onePieceRestApi.getAllLocations()
    }

    override suspend fun getAllSubLocationsByLocation(locationID: String): Response<SubLocationResponse> {
        return onePieceRestApi.getAllSubLocations(locationID)
    }


}