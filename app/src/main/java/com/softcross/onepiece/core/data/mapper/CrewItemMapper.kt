package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.network.dto.crew.CrewDto
import retrofit2.Response
import javax.inject.Inject

class CrewItemMapper @Inject constructor(): OnePieceResponseItemMapper<CrewDto, CrewEntity> {
    override fun map(input: Response<CrewDto>?): CrewEntity {
        return input?.body()?.run {
            CrewEntity(
                id = crewID.orEmpty(),
                crewName = crewName.orEmpty(),
                crewFlagURL = crewFlagURL.orEmpty(),
                crewMainShip = crewMainShip.orEmpty(),
                crewTotalBounty = crewTotalBounty.orEmpty()
            )
        } ?: throw Exception("Crew not found")
    }
}