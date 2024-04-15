package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.network.dto.location.LocationResponse
import retrofit2.Response
import javax.inject.Inject

class LocationListMapper @Inject constructor() :
    OnePieceResponseListMapper<LocationResponse, Location> {
    override fun map(input: Response<LocationResponse>?): List<Location> {
        return input?.body()?.results?.map {
            Location(
                it.locationID.orEmpty(),
                it.locationName.orEmpty(),
                it.locationPictureURL.orEmpty()
            )
        } ?: throw Exception("Locations not found")
    }
}