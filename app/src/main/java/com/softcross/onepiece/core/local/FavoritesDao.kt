package com.softcross.onepiece.core.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM FavoriteCharacters ORDER BY id ASC")
    fun getAllFavoriteCharacters(): Flow<List<FavoriteCharacterEntity>>

    @Insert
    suspend fun addFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity)

    @Query("DELETE FROM FavoriteCharacters WHERE id=:id")
    suspend fun deleteFavoriteCharacter(id: String)

    @Query("SELECT * FROM FavoriteCrews ORDER BY id ASC")
    fun getAllFavoriteCrews(): Flow<List<FavoriteCrewEntity>>

    @Insert
    suspend fun addFavoriteCrew(favoriteCrewEntity: FavoriteCrewEntity)

    @Query("DELETE FROM FavoriteCrews WHERE id=:id")
    suspend fun deleteFavoriteCrew(id: String)

    @Query("SELECT * FROM FavoriteDevilFruits ORDER BY id ASC")
    fun getAllFavoriteDevilFruits(): Flow<List<FavoriteDevilFruitEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteDevilFruits WHERE id = :devilFruitID)")
    suspend fun isFavoriteDevilFruit(devilFruitID: String): Boolean

    @Insert
    suspend fun addFavoriteDevilFruit(devilFruitEntity: FavoriteDevilFruitEntity)

    @Query("DELETE FROM FavoriteDevilFruits WHERE id=:id")
    suspend fun deleteFavoriteDevilFruit(id: String)

    @Query("SELECT * FROM FavoriteOccupations ORDER BY id ASC")
    fun getAllFavoriteOccupations(): Flow<List<FavoriteOccupationEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteOccupations WHERE id = :occupationID)")
    suspend fun isFavoriteOccupation(occupationID: String): Boolean

    @Insert
    suspend fun addFavoriteOccupation(favoriteOccupationEntity: FavoriteOccupationEntity)

    @Query("DELETE FROM FavoriteOccupations WHERE id=:id")
    suspend fun deleteFavoriteOccupation(id: String)

    @Query("SELECT * FROM FavoriteSubLocations ORDER BY id ASC")
    fun getAllFavoriteSubLocations(): Flow<List<FavoriteSubLocationEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteSubLocations WHERE id = :subLocationID)")
    suspend fun isFavoriteSubLocation(subLocationID: String): Boolean

    @Insert
    suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: FavoriteSubLocationEntity)

    @Query("DELETE FROM FavoriteSubLocations WHERE id=:id")
    suspend fun deleteFavoriteSubLocation(id: String)


}