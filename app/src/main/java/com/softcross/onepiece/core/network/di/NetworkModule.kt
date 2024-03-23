package com.softcross.onepiece.core.network.di

import com.softcross.onepiece.core.network.source.rest.OnePieceRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// In Kotlin, modules that only contain @Provides functions can be object classes.
// This way, providers get optimized and almost in-lined in generated code.
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //My local address
    private const val REST_API_BASE_URL = "http://10.0.2.2:3000/"

    //Provide the logging interceptor for the OkHttpClient,
    //For More level detail you can "alt + left" click "BODY"
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Provide OkHttpClient for the logging the request and response
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    //Provide retrofit for the all app
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(REST_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Provide api service
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): OnePieceRestApi =
        retrofit.create(OnePieceRestApi::class.java)
}