package com.softcross.onepiece.core.network.dto.location.sublocation

import com.google.gson.annotations.SerializedName

data class SubLocationOrigin(
    @SerializedName("locationName")
    val locationName:String?
)