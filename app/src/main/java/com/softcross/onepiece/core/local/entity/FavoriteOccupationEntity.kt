package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteOccupations")
data class FavoriteOccupationEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val desc: String,
    val imageURL: String
)