package com.softcross.onepiece.core.network.dto.location.sublocation

import com.google.gson.annotations.SerializedName

data class SubLocationDto(
    @SerializedName("_id")
    val subLocationID: String?,
    @SerializedName("firstAppearance")
    val firstAppearance: String?,
    @SerializedName("location")
    val location: SubLocationOrigin?,
    @SerializedName("subLocationName")
    val subLocationName: String?,
    @SerializedName("subLocationPictureURL")
    val subLocationPictureURL: String?
)