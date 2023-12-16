package com.softcross.onepiece.core.network.dto.character

import com.google.gson.annotations.SerializedName

data class CharacterOccupation(
    @SerializedName("occupationName")
    val occupationName: String?
)
