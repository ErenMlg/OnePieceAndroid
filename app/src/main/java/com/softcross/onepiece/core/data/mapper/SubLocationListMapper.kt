package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.network.dto.location.sublocation.SubLocationResponse
import retrofit2.Response
import javax.inject.Inject

class SubLocationListMapper @Inject constructor(): OnePieceResponseListMapper<SubLocationResponse, SubLocation> {
    override fun map(input: Response<SubLocationResponse>?): List<SubLocation> {
        return input?.body()?.results?.map {
            SubLocation(
                it.subLocationID.orEmpty(),
                it.subLocationName.orEmpty(),
                it.subLocationPictureURL.orEmpty(),
                it.firstAppearance.orEmpty(),
                it.location?.locationName.orEmpty()
            )
        } ?: throw Exception("Sub Locations not found")
    }
}