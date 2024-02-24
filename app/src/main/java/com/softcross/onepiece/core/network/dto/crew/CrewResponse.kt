package com.softcross.onepiece.core.network.dto.crew

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info

data class CrewResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<CrewDto>
)

