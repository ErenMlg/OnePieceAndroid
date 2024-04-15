package com.softcross.onepiece.core.data.di

import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
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
    abstract fun bindCharacterListMapper(onePieceCharacterMapper: CharacterListMapper): OnePieceResponseListMapper<CharacterResponse, Character>

    @Binds
    @Singleton
    abstract fun bindCharacterItemMapper(characterItemMapper: CharacterItemMapper): OnePieceResponseItemMapper<CharacterDto, Character>

    @Binds
    @Singleton
    abstract fun bindCrewListMapper(crewListMapper: CrewListMapper): OnePieceResponseListMapper<CrewResponse, Crew>

    @Binds
    @Singleton
    abstract fun bindCrewItemMapper(crewItemMapper: CrewItemMapper): OnePieceResponseItemMapper<CrewDto, Crew>

    @Binds
    @Singleton
    abstract fun bindDevilFruitListMapper(devilFruitListMapper: DevilFruitListMapper): OnePieceResponseListMapper<DevilFruitResponse, DevilFruit>

    @Binds
    @Singleton
    abstract fun bindOccupationListMapper(occupationListMapper: OccupationListMapper): OnePieceResponseListMapper<OccupationResponse, Occupations>

    @Binds
    @Singleton
    abstract fun bindLocationsListMapper(locationListMapper: LocationListMapper): OnePieceResponseListMapper<LocationResponse, Location>

    @Binds
    @Singleton
    abstract fun bindSubLocationsMapper(subLocationListMapper: SubLocationListMapper): OnePieceResponseListMapper<SubLocationResponse, SubLocation>
}