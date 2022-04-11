package com.lnsantos.stock.apresentation.company.listings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lnsantos.stock.apresentation.company.listings.esv.CompanyListingsEvent.OnSearchQueryChange
import com.lnsantos.stock.apresentation.company.listings.esv.CompanyListingsEvent.Refresh
import com.lnsantos.stock.apresentation.company.listings.esv.CompanyListingsViewModel
import com.lnsantos.stock.apresentation.components.SearchTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun CompanyListingsScreen(
    navigation: DestinationsNavigator,
    viewModel: CompanyListingsViewModel = hiltViewModel()
){
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefresh
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        SearchTextField(
            value = viewModel.state.query,
            setChangeValue = { newQuery ->
                viewModel.onEvent(OnSearchQueryChange(newQuery))
            }
        )
        Spacer(modifier = Modifier.padding(top = Dp(16f)))
        CompanySwipeList(
            swipeRefreshState = swipeRefreshState,
            swipeOnRefresh = { viewModel.onEvent(Refresh) },
            itemClicked = {  },
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
        )
    }
}
