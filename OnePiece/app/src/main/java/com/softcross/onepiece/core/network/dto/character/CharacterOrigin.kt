package com.softcross.onepiece.core.network.dto.character

import com.google.gson.annotations.SerializedName

data class CharacterOrigin(
    @SerializedName("subLocationName")
    val subLocationName:String?
)