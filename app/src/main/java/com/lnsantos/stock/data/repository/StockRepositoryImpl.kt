package com.lnsantos.stock.data.repository

import com.lnsantos.stock.data.csv.CSVParser
import com.lnsantos.stock.data.csv.CompanyParser
import com.lnsantos.stock.data.local.StockDatabase
import com.lnsantos.stock.data.mapper.toDomain
import com.lnsantos.stock.data.mapper.toEntity
import com.lnsantos.stock.data.remote.StockService
import com.lnsantos.stock.domain.model.CompanyListingDomain
import com.lnsantos.stock.domain.repository.StockRepository
import com.lnsantos.stock.util.*
import com.lnsantos.stock.util.ResultService.Loading
import com.lnsantos.stock.util.emitSuccess
import com.lnsantos.stock.util.startLoading
import com.lnsantos.stock.util.stopLoading
import com.opencsv.CSVReader
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val service: StockService,
    private val database: StockDatabase,
    private val companyCsv: CSVParser<CompanyListingDomain>
) : StockRepository {

    override suspend fun getCompany(
        canFetchFromRemote: Boolean,
        query: String
    ): FlowResultService<List<CompanyListingDomain>> {
        return flow {
            startLoading()

            val dao = database.dao
            val dataLocal = dao.searchCompany(query)
            val dataDomain = dataLocal.map { it.toDomain() }

            emitSuccess(dataDomain)

            val isEmptyDataLocal = dataDomain.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = isEmptyDataLocal.not() && canFetchFromRemote.not()

            if (shouldJustLoadFromCache) {
                stopLoading()
                return@flow
            }

            try {
                val response = service.getListings(query)
                val dataCompanyList = companyCsv.parser(response.byteStream())

                if (dataCompanyList.isEmpty()) {
                    stopLoading()
                    return@flow
                }

                val dataCompanyEntityList = dataCompanyList.map { it.toEntity() }

                dao.clearCompanyTable()
                dao.insertCompany(dataCompanyEntityList)

                stopLoading()
                emitSuccess(dataCompanyList)

            } catch (e: Throwable) {
                emitFailed(e.message, null)
            }

        }
    }
}