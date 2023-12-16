package com.softcross.onepiece.core.network.dto.occupation

import com.google.gson.annotations.SerializedName

data class OccupationDto(
    @SerializedName("_id")
    val occupationID: String?,
    @SerializedName("occupationDescription")
    val occupationDescription: String?,
    @SerializedName("occupationName")
    val occupationName: String?,
    @SerializedName("occupationPictureURL")
    val occupationPictureURL: String?
)