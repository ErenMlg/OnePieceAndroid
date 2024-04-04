package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import com.softcross.onepiece.core.network.dto.location.sublocation.SubLocationResponse
import retrofit2.Response
import javax.inject.Inject

class SubLocationListMapper @Inject constructor(): OnePieceResponseListMapper<SubLocationResponse, SubLocationEntity> {
    override fun map(input: Response<SubLocationResponse>?): List<SubLocationEntity> {
        return input?.body()?.results?.map {
            SubLocationEntity(
                it.subLocationID.orEmpty(),
                it.subLocationName.orEmpty(),
                it.subLocationPictureURL.orEmpty(),
                it.firstAppearance.orEmpty(),
                it.location?.locationName.orEmpty()
            )
        } ?: throw Exception("Sub Locations not found")
    }
}