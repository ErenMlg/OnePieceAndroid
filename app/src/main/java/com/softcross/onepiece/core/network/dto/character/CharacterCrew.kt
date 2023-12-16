package com.softcross.onepiece.core.network.dto.character

import com.google.gson.annotations.SerializedName

data class CharacterCrew(
    @SerializedName("crewName")
    val crewName:String?
)
