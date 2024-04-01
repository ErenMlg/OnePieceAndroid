package com.softcross.onepiece.core.data.repository

import com.softcross.onepiece.core.common.mapper.OnePieceMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.core.data.entity.LocationEntity
import com.softcross.onepiece.core.data.entity.OccupationsEntity
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import com.softcross.onepiece.core.data.mapper.OnePieceCharacterItemMapper
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import com.softcross.onepiece.core.network.source.rest.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnePieceRepositoryImpl @Inject constructor(
    private val restDataSource: RestDataSource,
    private val characterListMapper: OnePieceResponseListMapper<CharacterResponse, CharacterEntity>,
    private val characterItemMapper: OnePieceResponseItemMapper<CharacterDto, CharacterEntity>
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
        TODO("Not yet implemented")
    }

    override suspend fun getSingleCrew(id: String): Flow<ResponseState<CrewEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruitEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleDevilFruit(id: String): Flow<ResponseState<DevilFruitEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllLocations(): Flow<ResponseState<List<LocationEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleLocation(id: String): Flow<ResponseState<LocationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSubLocations(): Flow<ResponseState<List<SubLocationEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleSubLocation(id: String): Flow<ResponseState<SubLocationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllOccupations(): Flow<ResponseState<List<OccupationsEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleOccupation(id: String): Flow<ResponseState<OccupationsEntity>> {
        TODO("Not yet implemented")
    }
}