package com.softcross.onepiece.core.data.repository

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.data.modal.Location
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.core.data.mapper.CharacterItemMapper
import com.softcross.onepiece.core.data.mapper.CharacterListMapper
import com.softcross.onepiece.core.data.mapper.CrewItemMapper
import com.softcross.onepiece.core.data.mapper.CrewListMapper
import com.softcross.onepiece.core.data.mapper.DevilFruitListMapper
import com.softcross.onepiece.core.data.mapper.LocationListMapper
import com.softcross.onepiece.core.data.mapper.OccupationListMapper
import com.softcross.onepiece.core.data.mapper.SubLocationListMapper
import com.softcross.onepiece.core.local.LocalDataSource
import com.softcross.onepiece.core.network.source.rest.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnePieceRepositoryImpl @Inject constructor(
    private val restDataSource: RestDataSource,
    private val localDataSource: LocalDataSource,
    private val characterListMapper: CharacterListMapper,
    private val characterItemMapper: CharacterItemMapper,
    private val crewListMapper: CrewListMapper,
    private val crewItemMapper: CrewItemMapper,
    private val devilFruitListMapper: DevilFruitListMapper,
    private val occupationListMapper: OccupationListMapper,
    private val locationListMapper: LocationListMapper,
    private val subLocationListMapper: SubLocationListMapper
) : OnePieceRepository {
    override suspend fun getAllCharacters(): Flow<ResponseState<List<Character>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCharacters()
            emit(ResponseState.Success(characterListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCharacter(id: String): Flow<ResponseState<Character>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCharacter(id)
            emit(ResponseState.Success(characterItemMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteCharacters(): Flow<List<Character>> {
        return localDataSource.getAllFavoriteCharacters().map { it ->
            it.map {
                Character(
                    id = it.id.toString(),
                    characterName = it.name,
                    characterStatus = it.status,
                    characterOrigin = it.origin,
                    characterCrew = it.crew,
                    characterOccupation = it.occupation,
                    characterBounty = it.bounty,
                    characterDevilFruit = it.devilFruit,
                    characterAbilities = it.abilities,
                    characterPictureURL = it.imageURL
                )
            }
        }
    }

    override suspend fun addFavoriteCharacter(favoriteCharacterEntity: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteCharacter(favoriteCharacterEntity: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCrews(): Flow<ResponseState<List<Crew>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCrews()
            emit(ResponseState.Success(crewListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCrew(id: String): Flow<ResponseState<Crew>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCrew(id)
            emit(ResponseState.Success(crewItemMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteCrews(): Flow<List<Crew>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteCrew(favoriteCrewEntity: Crew) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteCrew(favoriteCrewEntity: Crew) {
        TODO("Not yet implemented")
    }


    override suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruit>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlDevilFruits()
            emit(ResponseState.Success(devilFruitListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteDevilFruits(): Flow<List<DevilFruit>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteDevilFruit(devilFruitEntity: DevilFruit) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteDevilFruit(devilFruitEntity: DevilFruit) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllOccupations(): Flow<ResponseState<List<Occupations>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlOccupations()
            emit(ResponseState.Success(occupationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteOccupations(): Flow<List<Occupations>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteOccupation(favoriteOccupationEntity: Occupations) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteOccupation(favoriteOccupationEntity: Occupations) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllLocations(): Flow<ResponseState<List<Location>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllLocations()
            emit(ResponseState.Success(locationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteLocations(): Flow<List<Location>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteLocation(favoriteLocationEntity: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteLocation(favoriteLocationEntity: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSubLocationsByLocation(locationID: String): Flow<ResponseState<List<SubLocation>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllSubLocationsByLocation(locationID)
            emit(ResponseState.Success(subLocationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllFavoriteSubLocations(): Flow<List<SubLocation>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteSubLocation(favoriteSubLocationEntity: SubLocation) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteSubLocation(favoriteSubLocationEntity: SubLocation) {
        TODO("Not yet implemented")
    }


}