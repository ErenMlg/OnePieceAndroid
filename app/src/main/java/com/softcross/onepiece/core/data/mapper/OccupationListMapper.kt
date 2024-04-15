package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class OccupationListMapper @Inject constructor() : OnePieceResponseListMapper<OccupationResponse, Occupations> {
    override fun map(input: Response<OccupationResponse>?): List<Occupations> {
        return input?.body()?.results?.map {
            Occupations(
                it.occupationID.orEmpty(),
                it.occupationName.orEmpty(),
                it.occupationDescription.orEmpty(),
                it.occupationPictureURL.orEmpty(),
            )
        } ?: throw Exception("Occupations not found")
    }
}