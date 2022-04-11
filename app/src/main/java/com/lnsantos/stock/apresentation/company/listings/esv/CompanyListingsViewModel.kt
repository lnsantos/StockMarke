package com.lnsantos.stock.apresentation.company.listings.esv

import com.lnsantos.stock.apresentation.company.listings.esv.CompanyListingsEvent.Refresh
import com.lnsantos.stock.apresentation.company.listings.esv.CompanyListingsEvent.OnSearchQueryChange
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnsantos.stock.domain.repository.StockRepository
import com.lnsantos.stock.util.isFailed
import com.lnsantos.stock.util.isLoading
import com.lnsantos.stock.util.isSuccess
import com.lnsantos.stock.util.toCompanyListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(CompanyListingsState())
    var searchJob: Job? = null

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is Refresh -> getCompanies(canFetchFromRemote = true)
            is OnSearchQueryChange -> searchQueryChange(event.query)
            else -> Unit
        }
    }

    private fun searchQueryChange(query: String) {
//        searchJob?.cancel()
//        searchJob = null
        state.query = query
        searchJob = viewModelScope.launch {
            delay(500L)
            getCompanies()
        }
    }

    private fun getCompanies(
        query: String = state.query.lowercase(Locale.getDefault()),
        canFetchFromRemote: Boolean = false
    ) = viewModelScope.launch {
        repository.getCompany(
            canFetchFromRemote = canFetchFromRemote,
            query = query
        ).also {
            it.isSuccess { data ->
                state = state.copy(data = data ?: arrayListOf())
            }.isLoading { isLoading ->
                state = state.copy(isLoading = isLoading)
            }.isFailed { description ->
                Unit
            }
        }
    }

}
