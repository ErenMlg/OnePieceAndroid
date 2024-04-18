package com.softcross.onepiece.core.local.di

import com.softcross.onepiece.core.local.LocalDataSource
import com.softcross.onepiece.core.local.LocalDataSourceImpl
import com.softcross.onepiece.core.network.source.rest.RestDataSource
import com.softcross.onepiece.core.network.source.rest.RestDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource


}