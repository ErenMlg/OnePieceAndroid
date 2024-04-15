package com.softcross.onepiece.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteCharacters")
data class FavoriteCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String,
    val bounty: String,
    val abilities: String,
    val crew: String,
    val imageURL: String,
    val devilFruit: String,
    val occupation: String,
    val origin: String
)