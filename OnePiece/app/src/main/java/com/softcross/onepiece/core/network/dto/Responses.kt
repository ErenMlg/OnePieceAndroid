package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.character.CharacterDto

data class Responses(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results:List<DtoObject>?
)