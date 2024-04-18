package com.softcross.onepiece.core.data.repository

import com.softcross.onepiece.core.common.extension.mapExtensions.toCharacter
import com.softcross.onepiece.core.common.extension.mapExtensions.toCharacterList
import com.softcross.onepiece.core.common.extension.mapExtensions.toCrew
import com.softcross.onepiece.core.common.extension.mapExtensions.toCrewList
import com.softcross.onepiece.core.common.extension.mapExtensions.toDevilFruit
import com.softcross.onepiece.core.common.extension.mapExtensions.toDevilFruitList
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteCharacter
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteCrew
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteDevilFruit
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteLocation
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteOccupation
import com.softcross.onepiece.core.common.extension.mapExtensions.toFavoriteSubLocation
import com.softcross.onepiece.core.common.extension.mapExtensions.toLocation
import com.softcross.onepiece.core.common.extension.mapExtensions.toLocationList
import com.softcross.onepiece.core.common.extension.mapExtensions.toOccupation
import com.softcross.onepiece.core.common.extension.mapExtensions.toOccupationList
import com.softcross.onepiece.core.common.extension.mapExtensions.toSubLocation
import com.softcross.onepiece.core.common.extension.mapExtensions.toSubLocationList
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.local.LocalDataSource
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.network.source.rest.RestDataSource
import com.softcross.onepiece.presentation.devilFruits.DevilFruitUiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnePieceRepositoryImpl @Inject constructor(
    private val restDataSource: RestDataSource,
    private val localDataSource: LocalDataSource
) : OnePieceRepository {
    override suspend fun getAllCharacters(): Flow<ResponseState<List<Character>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCharacters()
            emit(ResponseState.Success(response.toCharacterList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCharacter(id: String): Flow<ResponseState<Character>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCharacter(id)
            emit(ResponseState.Success(response.toCharacter()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteCharacters(): Flow<List<Character>> {
        return localDataSource.getAllFavoriteCharacters()
            .map { list -> list.map { item -> item.toCharacter() } }
    }

    override suspend fun addFavoriteCharacter(favoriteCharacterEntity: Character) {
        localDataSource.addFavoriteCharacter(favoriteCharacterEntity.toFavoriteCharacter())
    }

    override suspend fun deleteFavoriteCharacter(id: String) {
        localDataSource.deleteFavoriteCharacter(id)
    }

    override suspend fun getAllCrews(): Flow<ResponseState<List<Crew>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCrews()
            emit(ResponseState.Success(response.toCrewList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCrew(id: String): Flow<ResponseState<Crew>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCrew(id)
            emit(ResponseState.Success(response.toCrew()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteCrews(): Flow<List<Crew>> {
        return localDataSource.getAllFavoriteCrews()
            .map { list -> list.map { item -> item.toCrew() } }
    }

    override suspend fun addFavoriteCrew(favoriteCrewEntity: Crew) {
        localDataSource.addFavoriteCrew(favoriteCrewEntity.toFavoriteCrew())
    }

    override suspend fun deleteFavoriteCrew(id: String) {
        localDataSource.deleteFavoriteCrew(id)
    }


    override suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruit>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlDevilFruits()
            emit(ResponseState.Success(response.toDevilFruitList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun isFavoriteDevilFruit(devilFruitID: String): Boolean {
        return localDataSource.isFavoriteDevilFruit(devilFruitID)
    }

    override fun getAllFavoriteDevilFruits(): Flow<List<DevilFruit>> {
        return localDataSource.getAllFavoriteDevilFruits()
            .map { list -> list.map { item -> item.toDevilFruit() } }
    }

    override suspend fun addFavoriteDevilFruit(devilFruitEntity: DevilFruit) {
        localDataSource.addFavoriteDevilFruit(devilFruitEntity.toFavoriteDevilFruit())
    }

    override suspend fun deleteFavoriteDevilFruit(id: String) {
        localDataSource.deleteFavoriteDevilFruit(id)
    }

    override suspend fun getAllOccupations(): Flow<ResponseState<List<Occupations>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlOccupations()
            emit(ResponseState.Success(response.toOccupationList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteOccupations(): Flow<List<Occupations>> {
        return localDataSource.getAllFavoriteOccupations()
            .map { list -> list.map { item -> item.toOccupation() } }
    }

    override suspend fun isFavoriteOccupation(occupationID: String): Boolean {
        return localDataSource.isFavoriteOccupation(occupationID)
    }

    override suspend fun addFavoriteOccupation(favoriteOccupationEntity: Occupations) {
        localDataSource.addFavoriteOccupation(favoriteOccupationEntity.toFavoriteOccupation())
    }

    override suspend fun deleteFavoriteOccupation(id: String) {
        localDataSource.deleteFavoriteOccupation(id)
    }

    override suspend fun getAllLocations(): Flow<ResponseState<List<Location>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllLocations()
            emit(ResponseState.Success(response.toLocationList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllSubLocationsByLocation(locationID: String): Flow<ResponseState<List<SubLocation>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllSubLocationsByLocation(locationID)
            emit(ResponseState.Success(response.toSubLocationList()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun isFavoriteSubLocation(subLocationID: String): Boolean {
        return localDataSource.isFavoriteSubLocation(subLocationID)
    }

    override fun getAllFavoriteSubLocations(): Flow<List<SubLocation>> {
        return localDataSource.getAllFavoriteSubLocations()
            .map { list -> list.map { item -> item.toSubLocation() } }
    }

    override suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: SubLocation) {
        localDataSource.addFavoriteSubLocation(favoriteSubLocationEntity.toFavoriteSubLocation())
    }

    override suspend fun deleteFavoriteSubLocation(id: String) {
        localDataSource.deleteFavoriteSubLocation(id)
    }


}