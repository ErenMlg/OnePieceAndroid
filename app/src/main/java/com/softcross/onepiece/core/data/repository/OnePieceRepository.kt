package com.softcross.onepiece.core.data.repository

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.local.entity.FavoriteCrewEntity
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.local.entity.FavoriteLocationEntity
import com.softcross.onepiece.core.local.entity.FavoriteOccupationEntity
import com.softcross.onepiece.core.local.entity.FavoriteSubLocationEntity
import kotlinx.coroutines.flow.Flow


interface OnePieceRepository {

    //Characters
    suspend fun getAllCharacters(): Flow<ResponseState<List<Character>>>
    suspend fun getSingleCharacter(id: String): Flow<ResponseState<Character>>
    fun getAllFavoriteCharacters(): Flow<List<Character>>
    suspend fun addFavoriteCharacter(favoriteCharacterEntity: Character)
    suspend fun deleteFavoriteCharacter(favoriteCharacterEntity: Character)

    //Crews
    suspend fun getAllCrews(): Flow<ResponseState<List<Crew>>>
    suspend fun getSingleCrew(id: String): Flow<ResponseState<Crew>>
    fun getAllFavoriteCrews(): Flow<List<Crew>>
    suspend fun addFavoriteCrew(favoriteCrewEntity: Crew)
    suspend fun deleteFavoriteCrew(favoriteCrewEntity: Crew)

    //Devil Fruit
    suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruit>>>
    fun getAllFavoriteDevilFruits(): Flow<List<DevilFruit>>
    suspend fun addFavoriteDevilFruit(devilFruitEntity: DevilFruit)
    suspend fun deleteFavoriteDevilFruit(devilFruitEntity: DevilFruit)

    //Location
    suspend fun getAllLocations(): Flow<ResponseState<List<Location>>>
    fun getAllFavoriteLocations(): Flow<List<Location>>
    suspend fun addFavoriteLocation(favoriteLocationEntity: Location)
    suspend fun deleteFavoriteLocation(favoriteLocationEntity: Location)

    //SubLocation
    suspend fun getAllSubLocationsByLocation(locationID: String): Flow<ResponseState<List<SubLocation>>>
    fun getAllFavoriteSubLocations(): Flow<List<SubLocation>>
    suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: SubLocation)
    suspend fun deleteFavoriteSubLocation(favoriteSubLocationEntity: SubLocation)

    //Occupation
    suspend fun getAllOccupations(): Flow<ResponseState<List<Occupations>>>
    fun getAllFavoriteOccupations(): Flow<List<Occupations>>
    suspend fun addFavoriteOccupation(favoriteOccupationEntity: Occupations)
    suspend fun deleteFavoriteOccupation(favoriteOccupationEntity: Occupations)

}