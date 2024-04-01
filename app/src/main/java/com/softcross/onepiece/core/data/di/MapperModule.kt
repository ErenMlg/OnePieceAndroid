package com.softcross.onepiece.core.data.di

import com.softcross.onepiece.core.common.mapper.OnePieceMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.mapper.OnePieceCharacterItemMapper
import com.softcross.onepiece.core.data.mapper.OnePieceCharacterListMapper
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCharacterListMapper(onePieceCharacterMapper: OnePieceCharacterListMapper): OnePieceResponseListMapper<CharacterResponse, CharacterEntity>

    @Binds
    @Singleton
    abstract fun bindCharacterItemMapper(onePieceCharacterItemMapper: OnePieceCharacterItemMapper): OnePieceResponseItemMapper<CharacterDto, CharacterEntity>

}