package com.softcross.onepiece.core.network.dto.location

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("_id")
    val locationID: String?,
    @SerializedName("locationName")
    val locationName: String?,
    @SerializedName("locationPictureURL")
    val locationPictureURL: String?
)