package com.softcross.onepiece.core.common.extension.mapExtensions

import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity
import com.softcross.onepiece.core.network.dto.location.LocationResponse
import com.softcross.onepiece.core.network.dto.location.sublocation.SubLocationResponse
import com.softcross.onepiece.presentation.locations.subLocations.SubLocationUiItem
import retrofit2.Response

fun Response<LocationResponse>.toLocationList(): List<Location> {
    return body()?.results?.map {
        Location(
            it.locationID.orEmpty(),
            it.locationName.orEmpty(),
            it.locationPictureURL.orEmpty()
        )
    } ?: throw Exception("Locations not found")
}

fun FavoriteLocationEntity.toLocation(): Location {
    return Location(id = id.toString(), locationName = name, locationPictureURL = imageURL)
}

fun Location.toFavoriteLocation(): FavoriteLocationEntity {
    return FavoriteLocationEntity(
        id = id,
        name = locationName,
        imageURL = locationPictureURL
    )
}

fun Response<SubLocationResponse>.toSubLocationList(): List<SubLocation> {
    return body()?.results?.map {
        SubLocation(
            it.subLocationID.orEmpty(),
            it.subLocationName.orEmpty(),
            it.subLocationPictureURL.orEmpty(),
            it.firstAppearance.orEmpty(),
            it.location?.locationName.orEmpty()
        )
    } ?: throw Exception("Sub Locations not found")
}

fun FavoriteSubLocationEntity.toSubLocation(): SubLocation {
    return SubLocation(
        id = id.toString(),
        subLocationName = name,
        subLocationPictureURL = imageURL,
        firstAppearance = firstApperance,
        locationName = locationName
    )
}

fun SubLocation.toFavoriteSubLocation(): FavoriteSubLocationEntity {
    return FavoriteSubLocationEntity(
        id = id,
        name = subLocationName,
        imageURL = subLocationPictureURL,
        firstApperance = firstAppearance,
        locationName = locationName
    )
}

fun SubLocationUiItem.toSubLocation(): SubLocation {
    return SubLocation(id, subLocationName, subLocationPictureURL, firstAppearance, locationName)
}