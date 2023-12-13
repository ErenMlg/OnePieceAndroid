package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class SubLocationOrigin(
    @SerializedName("locationName")
    val locationName: String?
)