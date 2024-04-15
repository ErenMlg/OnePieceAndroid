package com.softcross.onepiece.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity

@Database(
    entities =
    [
        FavoriteOccupationEntity::class,
        FavoriteLocationEntity::class,
        FavoriteSubLocationEntity::class,
        FavoriteCrewEntity::class,
        FavoriteDevilFruitEntity::class,
        FavoriteCharacterEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class OnePieceFavoriteDatabase : RoomDatabase(){
    abstract fun favoritesDao(): FavoritesDao
}