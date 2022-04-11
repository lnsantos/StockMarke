package com.lnsantos.stock.util

import com.lnsantos.stock.apresentation.company.listings.CompanyListingsState
import com.lnsantos.stock.domain.model.CompanyListingDomain

infix fun FlowResultService<List<CompanyListingDomain>>.toCompanyListingState(state: CompanyListingsState){
    isSuccess { data ->
        state.data = data ?: arrayListOf()
    }
    isLoading { isLoading ->
        state.isLoading = isLoading
    }
    isFailed { description ->
        Unit
    }
}