package com.lnsantos.stock.DI

import com.lnsantos.stock.data.csv.CSVParser
import com.lnsantos.stock.data.csv.CompanyParser
import com.lnsantos.stock.domain.model.CompanyListingDomain
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCompanyParser(parser: CompanyParser) : CSVParser<CompanyListingDomain>

}