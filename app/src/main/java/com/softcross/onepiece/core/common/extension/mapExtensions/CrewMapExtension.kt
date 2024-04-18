package com.softcross.onepiece.core.common.extension.mapExtensions

import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.network.dto.crew.CrewDto
import com.softcross.onepiece.core.network.dto.crew.CrewResponse
import retrofit2.Response

fun Response<CrewResponse>.toCrewList(): List<Crew> {
    return body()?.results?.map {
        Crew(
            it.crewID.orEmpty(),
            it.crewName.orEmpty(),
            it.crewTotalBounty.orEmpty(),
            it.crewMainShip.orEmpty(),
            it.crewFlagURL.orEmpty()
        )
    } ?: throw Exception("Crews not found")
}

fun Response<CrewDto>.toCrew(): Crew {
    return body()?.run {
        Crew(
            id = crewID.orEmpty(),
            crewName = crewName.orEmpty(),
            crewFlagURL = crewFlagURL.orEmpty(),
            crewMainShip = crewMainShip.orEmpty(),
            crewTotalBounty = crewTotalBounty.orEmpty()
        )
    } ?: throw Exception("Crew not found")
}

fun FavoriteCrewEntity.toCrew(): Crew {
    return Crew(
        id = id.toString(),
        crewName = name,
        crewTotalBounty = totalBounty,
        crewMainShip = mainShip,
        crewFlagURL = imageURL
    )
}

fun Crew.toFavoriteCrew(): FavoriteCrewEntity {
    return FavoriteCrewEntity(
        id = id,
        name = crewName,
        totalBounty = crewTotalBounty,
        mainShip = crewMainShip,
        imageURL = crewFlagURL
    )
}
