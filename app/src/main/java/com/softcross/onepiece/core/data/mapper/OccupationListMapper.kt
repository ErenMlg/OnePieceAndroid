package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.OccupationsEntity
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class OccupationListMapper @Inject constructor() : OnePieceResponseListMapper<OccupationResponse, OccupationsEntity> {
    override fun map(input: Response<OccupationResponse>?): List<OccupationsEntity> {
        return input?.body()?.results?.map {
            OccupationsEntity(
                it.occupationID.orEmpty(),
                it.occupationName.orEmpty(),
                it.occupationDescription.orEmpty(),
                it.occupationPictureURL.orEmpty(),
            )
        } ?: throw Exception("Occupations not found")
    }
}