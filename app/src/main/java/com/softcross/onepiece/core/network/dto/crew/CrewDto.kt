package com.softcross.onepiece.core.network.dto.crew

import com.google.gson.annotations.SerializedName

data class CrewDto(
    @SerializedName("_id")
    val crewID: String?,
    @SerializedName("crewFlagURL")
    val crewFlagURL: String?,
    @SerializedName("crewMainShip")
    val crewMainShip: String?,
    @SerializedName("crewName")
    val crewName: String?,
    @SerializedName("crewTotalBounty")
    val crewTotalBounty: String?
)