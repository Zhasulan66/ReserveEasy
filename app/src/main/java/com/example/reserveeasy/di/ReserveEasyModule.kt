package com.example.reserveeasy.di

import com.example.reserveeasy.data.remote.ReserveEasyApiService
import com.example.reserveeasy.data.repository.ReserveEasyRepositoryImpl
import com.example.reserveeasy.domain.repository.ReserveEasyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReserveEasyModule {

    @Provides
    @Singleton
    fun provideReserveEasyApiService(): ReserveEasyApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://goplacekz.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ReserveEasyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideReserveEasyRepository(
        reserveEasyApiService: ReserveEasyApiService
    ) : ReserveEasyRepository {
        return ReserveEasyRepositoryImpl(reserveEasyApiService)
    }
}