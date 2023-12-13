package com.softcross.onepiece.core.network.dto

import com.google.gson.annotations.SerializedName

data class CrewDto(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("crewFlagURL")
    val crewFlagURL: String?,
    @SerializedName("crewMainShip")
    val crewMainShip: String?,
    @SerializedName("crewName")
    val crewName: String?,
    @SerializedName("crewTotalBounty")
    val crewTotalBounty: Long?
) : DtoObject