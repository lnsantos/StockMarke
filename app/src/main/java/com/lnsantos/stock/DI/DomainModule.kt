package com.lnsantos.stock.DI

import com.lnsantos.stock.data.repository.StockRepositoryImpl
import com.lnsantos.stock.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(repository: StockRepositoryImpl) : StockRepository

}