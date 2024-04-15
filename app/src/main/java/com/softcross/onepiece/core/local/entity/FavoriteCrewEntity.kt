package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteCrews")
data class FavoriteCrewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val totalBounty: String,
    val mainShip: String,
    val imageURL: String
)