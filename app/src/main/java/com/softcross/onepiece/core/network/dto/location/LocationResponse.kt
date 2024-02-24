package com.softcross.onepiece.core.network.dto.location

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitDto

data class LocationResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<LocationDto>
)
