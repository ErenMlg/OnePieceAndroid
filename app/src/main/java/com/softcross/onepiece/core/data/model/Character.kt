package com.softcross.onepiece.core.data.model

import com.softcross.onepiece.core.data.model.Character

data class Character(
    val id:String,
    val characterName:String,
    val characterStatus:String,
    val characterOrigin:String,
    val characterCrew:String,
    val characterOccupation:String,
    val characterLong:Int,
    val characterDevilFruit:String,
    val characterAbilities:String,
    val characterPictureURL:String
)