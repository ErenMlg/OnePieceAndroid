package com.softcross.onepiece.core.local

import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val favoritesDao: FavoritesDao) :
    LocalDataSource {
    override fun getAllFavoriteCharacters(): Flow<List<FavoriteCharacterEntity>> {
        return favoritesDao.getAllFavoriteCharacters()
    }

    override suspend fun addFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity) {
        favoritesDao.addFavoriteCharacter(favoriteCharacterEntity)
    }

    override suspend fun deleteFavoriteCharacter(id: String) {
        favoritesDao.deleteFavoriteCharacter(id)
    }

    override fun getAllFavoriteCrews(): Flow<List<FavoriteCrewEntity>> {
        return favoritesDao.getAllFavoriteCrews()
    }

    override suspend fun addFavoriteCrew(favoriteCrewEntity: FavoriteCrewEntity) {
        favoritesDao.addFavoriteCrew(favoriteCrewEntity)
    }

    override suspend fun deleteFavoriteCrew(id: String) {
        favoritesDao.deleteFavoriteCrew(id)
    }

    override fun getAllFavoriteDevilFruits(): Flow<List<FavoriteDevilFruitEntity>> {
        return favoritesDao.getAllFavoriteDevilFruits()
    }

    override suspend fun isFavoriteDevilFruit(devilFruitID: String): Boolean {
        return favoritesDao.isFavoriteDevilFruit(devilFruitID)
    }

    override suspend fun addFavoriteDevilFruit(devilFruitEntity: FavoriteDevilFruitEntity) {
        favoritesDao.addFavoriteDevilFruit(devilFruitEntity)
    }

    override suspend fun deleteFavoriteDevilFruit(id: String) {
        favoritesDao.deleteFavoriteDevilFruit(id)
    }

    override fun getAllFavoriteOccupations(): Flow<List<FavoriteOccupationEntity>> {
        return favoritesDao.getAllFavoriteOccupations()
    }

    override suspend fun isFavoriteOccupation(occupationID: String): Boolean {
        return favoritesDao.isFavoriteOccupation(occupationID)
    }

    override suspend fun addFavoriteOccupation(favoriteOccupationEntity: FavoriteOccupationEntity) {
        favoritesDao.addFavoriteOccupation(favoriteOccupationEntity)
    }

    override suspend fun deleteFavoriteOccupation(id: String) {
        favoritesDao.deleteFavoriteOccupation(id)
    }

    override fun getAllFavoriteSubLocations(): Flow<List<FavoriteSubLocationEntity>> {
        return favoritesDao.getAllFavoriteSubLocations()
    }

    override suspend fun isFavoriteSubLocation(subLocationID: String): Boolean {
        return favoritesDao.isFavoriteSubLocation(subLocationID)
    }

    override suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: FavoriteSubLocationEntity) {
        favoritesDao.addFavoriteSubLocation(favoriteSubLocationEntity)
    }

    override suspend fun deleteFavoriteSubLocation(id:String) {
        favoritesDao.deleteFavoriteSubLocation(id)
    }
}