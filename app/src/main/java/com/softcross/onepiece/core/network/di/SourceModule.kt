package com.softcross.onepiece.core.network.di

import com.softcross.onepiece.core.network.source.rest.RestDataSource
import com.softcross.onepiece.core.network.source.rest.RestDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// @Binds must annotate an abstract function (since it's abstract,
// it doesn't contain any code and the class needs to be abstract too).
@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    @Singleton
    abstract fun bindRestDataSource(restDataSourceImpl: RestDataSourceImpl) : RestDataSource

}