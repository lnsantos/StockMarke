package com.lnsantos.stock.apresentation.company.listings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.lnsantos.stock.domain.model.CompanyListingDomain

@Composable
fun CompanySwipeList(
    modifier: Modifier = Modifier,
    swipeRefreshState: SwipeRefreshState,
    swipeOnRefresh: () -> Unit,
    itemClicked: (CompanyListingDomain) -> Unit,
    data: List<CompanyListingDomain> = arrayListOf()
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = swipeOnRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(data.size) { index: Int ->
                val company = data[index]
                CompanyItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { itemClicked(company) },
                    company = company
                )

                if (index < data.size) {
                    Divider(
                        Modifier.padding(horizontal = Dp(16f))
                    )
                }
            }
        }
    }
}