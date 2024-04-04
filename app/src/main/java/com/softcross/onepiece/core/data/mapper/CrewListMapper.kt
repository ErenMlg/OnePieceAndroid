package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.network.dto.crew.CrewResponse
import retrofit2.Response
import javax.inject.Inject

class CrewListMapper @Inject constructor() :
    OnePieceResponseListMapper<CrewResponse, CrewEntity> {
    override fun map(input: Response<CrewResponse>?): List<CrewEntity> {
        return input?.body()?.results?.map {
            CrewEntity(
                it.crewID.orEmpty(),
                it.crewName.orEmpty(),
                it.crewTotalBounty.orEmpty(),
                it.crewMainShip.orEmpty(),
                it.crewFlagURL.orEmpty()
            )
        } ?: throw Exception("Crews not found")
    }
}