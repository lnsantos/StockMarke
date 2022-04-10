package com.lnsantos.stock.data.csv

import com.lnsantos.stock.domain.model.CompanyListingDomain
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CompanyParser @Inject constructor() : CSVParser<CompanyListingDomain> {

    override suspend fun parser(inputStream: InputStream): List<CompanyListingDomain> {
        val reader = CSVReader(InputStreamReader(inputStream))
        return withContext(Dispatchers.IO) {
            reader.run {
                readAll().drop(1).mapNotNull { row ->

                    val symbol = row.getOrNull(0) ?: return@mapNotNull null
                    val name = row.getOrNull(1) ?: return@mapNotNull null
                    val exchange = row.getOrNull(2) ?: return@mapNotNull null


                    CompanyListingDomain(name, symbol, exchange)
                }
            }.also {
                reader.close()
            }
        }
    }

}