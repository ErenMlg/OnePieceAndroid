package com.softcross.onepiece.core.network.dto.location.sublocation

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitDto

data class SubLocationResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<SubLocationDto>
)
