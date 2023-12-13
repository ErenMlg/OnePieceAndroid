package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("locationName")
    val locationName: String?,
    @SerializedName("locationPictureURL")
    val locationPictureURL: String?
) : DtoObject