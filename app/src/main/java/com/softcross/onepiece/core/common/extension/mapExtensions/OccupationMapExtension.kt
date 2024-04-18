package com.softcross.onepiece.core.common.extension.mapExtensions

import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import com.softcross.onepiece.presentation.occupations.OccupationUiItem
import retrofit2.Response
import java.lang.Exception

fun Response<OccupationResponse>.toOccupationList(): List<Occupations> {
    return body()?.results?.map {
        Occupations(
            it.occupationID.orEmpty(),
            it.occupationName.orEmpty(),
            it.occupationDescription.orEmpty(),
            it.occupationPictureURL.orEmpty(),
        )
    } ?: throw Exception("Occupations not found")
}

fun FavoriteOccupationEntity.toOccupation(): Occupations {
    return Occupations(
        id = id,
        occupationName = name,
        occupationDesc = desc,
        occupationPictureURL = imageURL
    )
}

fun Occupations.toFavoriteOccupation(): FavoriteOccupationEntity {
    return FavoriteOccupationEntity(
        id = id,
        name = occupationName,
        desc = occupationDesc,
        imageURL = occupationPictureURL
    )
}

fun OccupationUiItem.toOccupation(): Occupations {
    return Occupations(
        id, occupationName, occupationDesc, occupationPictureURL
    )
}