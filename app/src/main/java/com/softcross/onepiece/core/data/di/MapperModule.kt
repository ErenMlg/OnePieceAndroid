package com.softcross.onepiece.core.data.di

import com.softcross.onepiece.core.common.mapper.OnePieceResponseMapper
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.data.mapper.OnePieceCharacterMapper
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
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
    abstract fun bindCharacterMapper(onePieceCharacterMapper: OnePieceCharacterMapper): OnePieceResponseMapper<CharacterResponse, CharacterEntity>

}