package com.softcross.onepiece.core.network.dto.devilFruit

import com.google.gson.annotations.SerializedName
import com.softcross.onepiece.core.network.dto.Info

data class DevilFruitResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<DevilFruitDto>
)



