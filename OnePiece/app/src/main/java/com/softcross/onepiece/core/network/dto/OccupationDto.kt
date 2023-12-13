package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class OccupationDto(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("occupationDescription")
    val occupationDescription: String?,
    @SerializedName("occupationName")
    val occupationName: String?,
    @SerializedName("occupationPictureURL")
    val occupationPictureURL: String?
) : DtoObject