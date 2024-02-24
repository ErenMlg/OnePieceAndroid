package com.softcross.onepiece.core.data.di

import com.softcross.onepiece.core.data.repository.OnePieceRepository
import com.softcross.onepiece.core.data.repository.OnePieceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnePieceRepository(onePieceRepositoryImpl: OnePieceRepositoryImpl): OnePieceRepository

}