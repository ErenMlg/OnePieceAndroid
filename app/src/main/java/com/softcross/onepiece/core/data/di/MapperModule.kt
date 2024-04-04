package com.softcross.onepiece.core.data.di

import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
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
import com.softcross.onepiece.core.network.dto.location.LocationResponse
import com.softcross.onepiece.core.network.dto.location.sublocation.SubLocationResponse
import com.softcross.onepiece.core.network.dto.occupation.OccupationResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCharacterListMapper(onePieceCharacterMapper: CharacterListMapper): OnePieceResponseListMapper<CharacterResponse, CharacterEntity>

    @Binds
    @Singleton
    abstract fun bindCharacterItemMapper(characterItemMapper: CharacterItemMapper): OnePieceResponseItemMapper<CharacterDto, CharacterEntity>

    @Binds
    @Singleton
    abstract fun bindCrewListMapper(crewListMapper: CrewListMapper): OnePieceResponseListMapper<CrewResponse, CrewEntity>

    @Binds
    @Singleton
    abstract fun bindCrewItemMapper(crewItemMapper: CrewItemMapper): OnePieceResponseItemMapper<CrewDto, CrewEntity>

    @Binds
    @Singleton
    abstract fun bindDevilFruitListMapper(devilFruitListMapper: DevilFruitListMapper): OnePieceResponseListMapper<DevilFruitResponse, DevilFruitEntity>

    @Binds
    @Singleton
    abstract fun bindOccupationListMapper(occupationListMapper: OccupationListMapper): OnePieceResponseListMapper<OccupationResponse, OccupationsEntity>

    @Binds
    @Singleton
    abstract fun bindLocationsListMapper(locationListMapper: LocationListMapper): OnePieceResponseListMapper<LocationResponse, LocationEntity>

    @Binds
    @Singleton
    abstract fun bindSubLocationsMapper(subLocationListMapper: SubLocationListMapper): OnePieceResponseListMapper<SubLocationResponse, SubLocationEntity>
}