package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteDevilFruits")
data class FavoriteDevilFruitEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val imageURL: String
)