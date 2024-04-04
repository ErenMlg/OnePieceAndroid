package com.softcross.onepiece.core.data.repository

import android.util.Log
import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.core.data.entity.LocationEntity
import com.softcross.onepiece.core.data.entity.OccupationsEntity
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import com.softcross.onepiece.core.data.mapper.CharacterItemMapper
import com.softcross.onepiece.core.data.mapper.CharacterListMapper
import com.softcross.onepiece.core.data.mapper.CrewItemMapper
import com.softcross.onepiece.core.data.mapper.CrewListMapper
import com.softcross.onepiece.core.data.mapper.DevilFruitListMapper
import com.softcross.onepiece.core.data.mapper.LocationListMapper
import com.softcross.onepiece.core.data.mapper.OccupationListMapper
import com.softcross.onepiece.core.data.mapper.SubLocationListMapper
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import com.softcross.onepiece.core.network.dto.crew.CrewDto
import com.softcross.onepiece.core.network.dto.crew.CrewResponse
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitResponse
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import com.softcross.onepiece.core.network.source.rest.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnePieceRepositoryImpl @Inject constructor(
    private val restDataSource: RestDataSource,
    private val characterListMapper: CharacterListMapper,
    private val characterItemMapper: CharacterItemMapper,
    private val crewListMapper: CrewListMapper,
    private val crewItemMapper: CrewItemMapper,
    private val devilFruitListMapper: DevilFruitListMapper,
    private val occupationListMapper: OccupationListMapper,
    private val locationListMapper: LocationListMapper,
    private val subLocationListMapper: SubLocationListMapper
) : OnePieceRepository {
    override suspend fun getAllCharacters(): Flow<ResponseState<List<CharacterEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCharacters()
            emit(ResponseState.Success(characterListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCharacter(id: String): Flow<ResponseState<CharacterEntity>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCharacter(id)
            emit(ResponseState.Success(characterItemMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllCrews(): Flow<ResponseState<List<CrewEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllCrews()
            emit(ResponseState.Success(crewListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleCrew(id: String): Flow<ResponseState<CrewEntity>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleCrew(id)
            emit(ResponseState.Success(crewItemMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }


    override suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruitEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlDevilFruits()
            emit(ResponseState.Success(devilFruitListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllOccupations(): Flow<ResponseState<List<OccupationsEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getALlOccupations()
            emit(ResponseState.Success(occupationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllLocations(): Flow<ResponseState<List<LocationEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllLocations()
            emit(ResponseState.Success(locationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllSubLocationsByLocation(locationID: String): Flow<ResponseState<List<SubLocationEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllSubLocationsByLocation(locationID)
            emit(ResponseState.Success(subLocationListMapper.map(response)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }


}