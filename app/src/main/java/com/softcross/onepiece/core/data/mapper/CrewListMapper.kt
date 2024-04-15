package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.network.dto.crew.CrewResponse
import retrofit2.Response
import javax.inject.Inject

class CrewListMapper @Inject constructor() :
    OnePieceResponseListMapper<CrewResponse, Crew> {
    override fun map(input: Response<CrewResponse>?): List<Crew> {
        return input?.body()?.results?.map {
            Crew(
                it.crewID.orEmpty(),
                it.crewName.orEmpty(),
                it.crewTotalBounty.orEmpty(),
                it.crewMainShip.orEmpty(),
                it.crewFlagURL.orEmpty()
            )
        } ?: throw Exception("Crews not found")
    }
}