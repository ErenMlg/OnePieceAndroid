package com.softcross.onepiece.core.local

import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    //Character
    fun getAllFavoriteCharacters(): Flow<List<FavoriteCharacterEntity>>
    suspend fun addFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity)
    suspend fun deleteFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity)

    //Crew
    fun getAllFavoriteCrews(): Flow<List<FavoriteCrewEntity>>
    suspend fun addFavoriteCrew(favoriteCrewEntity: FavoriteCrewEntity)
    suspend fun deleteFavoriteCrew(favoriteCrewEntity: FavoriteCrewEntity)

    //Devil Fruit
    fun getAllFavoriteDevilFruits(): Flow<List<FavoriteDevilFruitEntity>>
    suspend fun isFavoriteDevilFruit(devilFruitID:String): Boolean
    suspend fun addFavoriteDevilFruit(devilFruitEntity: FavoriteDevilFruitEntity)
    suspend fun deleteFavoriteDevilFruit(devilFruitEntity: FavoriteDevilFruitEntity)

    //Occupation
    fun getAllFavoriteOccupations(): Flow<List<FavoriteOccupationEntity>>
    suspend fun isFavoriteOccupation(occupationID:String): Boolean
    suspend fun addFavoriteOccupation(favoriteOccupationEntity: FavoriteOccupationEntity)
    suspend fun deleteFavoriteOccupation(favoriteOccupationEntity: FavoriteOccupationEntity)

    //Sub Location
    fun getAllFavoriteSubLocations(): Flow<List<FavoriteSubLocationEntity>>
    suspend fun isFavoriteSubLocation(subLocationID:String): Boolean

    suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: FavoriteSubLocationEntity)
    suspend fun deleteFavoriteSubLocation(favoriteSubLocationEntity: FavoriteSubLocationEntity)
}