package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteSubLocations")
data class FavoriteSubLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageURL: String,
    val firstApperance: String,
    val locationName: String
)