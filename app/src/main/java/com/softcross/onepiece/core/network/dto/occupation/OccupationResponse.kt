package com.softcross.onepiece.core.network.dto.occupation

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitDto

data class OccupationResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<OccupationDto>
)
