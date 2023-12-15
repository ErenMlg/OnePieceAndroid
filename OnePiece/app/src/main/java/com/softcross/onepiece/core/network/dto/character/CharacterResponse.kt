package com.softcross.onepiece.core.network.dto.character

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info

data class CharacterResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<CharacterDto>
)

