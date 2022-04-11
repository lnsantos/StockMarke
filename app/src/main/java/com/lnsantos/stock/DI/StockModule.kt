package com.lnsantos.stock.DI

import android.app.Application
import androidx.room.Room
import com.lnsantos.stock.data.local.StockDatabase
import com.lnsantos.stock.data.remote.StockService
import com.lnsantos.stock.data.remote.StockService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockModule {

    @Provides
    @Singleton
    fun providerStockService(): StockService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun provideDatabase(application: Application): StockDatabase = Room.databaseBuilder(
        application,
        StockDatabase::class.java,
        "database.stock.application"
    ).build()

}