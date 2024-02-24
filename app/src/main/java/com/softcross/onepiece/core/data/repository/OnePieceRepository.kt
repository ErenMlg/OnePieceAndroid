package com.softcross.onepiece.core.data.repository

import com.softcross.onepiece.core.data.ResponseState
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.core.data.entity.LocationEntity
import com.softcross.onepiece.core.data.entity.OccupationsEntity
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import kotlinx.coroutines.flow.Flow


interface OnePieceRepository {

    suspend fun getAllCharacters(): Flow<ResponseState<List<CharacterEntity>>>
    suspend fun getSingleCharacter(id: String): Flow<ResponseState<CharacterEntity>>

    suspend fun getAllCrews(): Flow<ResponseState<List<CrewEntity>>>
    suspend fun getSingleCrew(id: String): Flow<ResponseState<CrewEntity>>

    suspend fun getAllDevilFruits(): Flow<ResponseState<List<DevilFruitEntity>>>
    suspend fun getSingleDevilFruit(id: String): Flow<ResponseState<DevilFruitEntity>>

    suspend fun getAllLocations(): Flow<ResponseState<List<LocationEntity>>>
    suspend fun getSingleLocation(id: String): Flow<ResponseState<LocationEntity>>

    suspend fun getAllSubLocations(): Flow<ResponseState<List<SubLocationEntity>>>
    suspend fun getSingleSubLocation(id: String): Flow<ResponseState<SubLocationEntity>>

    suspend fun getAllOccupations(): Flow<ResponseState<List<OccupationsEntity>>>
    suspend fun getSingleOccupation(id: String): Flow<ResponseState<OccupationsEntity>>

}