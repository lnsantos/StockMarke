package com.lnsantos.stock.apresentation.company.listings

sealed class CompanyListingsEvent {
    object Refresh : CompanyListingsEvent()
    data class OnSearchQueryChange(
        val query: String
    ) : CompanyListingsEvent()
}
