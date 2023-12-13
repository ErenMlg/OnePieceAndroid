package com.softcross.onepiece.core.network.dto.character

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("_id")
    val characterID:String?,
    @SerializedName("characterName")
    val characterName: String?,
    @SerializedName("characterStatus")
    val characterStatus:String?,
    @SerializedName("characterCrew")
    val characterCrew: CharacterCrew?,
    @SerializedName("characterOrigin")
    val characterOrigin: CharacterOrigin?,
    @SerializedName("characterOccupation")
    val characterOccupation: CharacterOccupation?,
    @SerializedName("characterBounty")
    val characterBounty: Long?,
    @SerializedName("characterDevilFruit")
    val characterDevilFruit: CharacterDevilFruit?,
    @SerializedName("characterAbilities")
    val characterAbilities:String?,
    @SerializedName("characterPictureURL")
    val characterPicture:String?
)