package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    val count:Int?
)