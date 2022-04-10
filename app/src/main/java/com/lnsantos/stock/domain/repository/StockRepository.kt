package com.lnsantos.stock.domain.repository

import com.lnsantos.stock.domain.model.CompanyListingDomain
import com.lnsantos.stock.util.FlowResultService

interface StockRepository {

    suspend fun getCompany(
        canFetchFromRemote: Boolean = false,
        query: String = String()
    ) : FlowResultService<CompanyListingDomain>

}