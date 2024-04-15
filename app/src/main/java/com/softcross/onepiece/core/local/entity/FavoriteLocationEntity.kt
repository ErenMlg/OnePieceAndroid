package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteLocations")
data class FavoriteLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageURL: String
)