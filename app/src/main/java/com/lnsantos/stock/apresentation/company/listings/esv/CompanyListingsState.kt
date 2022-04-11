package com.lnsantos.stock.apresentation.company.listings.esv

import com.lnsantos.stock.domain.model.CompanyListingDomain

data class CompanyListingsState(
    var data: List<CompanyListingDomain> = arrayListOf(),
    var isRefresh: Boolean = false,
    var isLoading: Boolean = false,
    var query: String = String()
)
