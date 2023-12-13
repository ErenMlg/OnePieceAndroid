package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class DevilFruitDto(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("devilFruitName")
    val devilFruitName: String?,
    @SerializedName("devilFruitPictureURL")
    val devilFruitPictureURL: String?,
    @SerializedName("devilFruitType")
    val devilFruitType: String?
) : DtoObject