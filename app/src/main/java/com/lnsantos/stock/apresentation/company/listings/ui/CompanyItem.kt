package com.lnsantos.stock.apresentation.company.listings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lnsantos.stock.domain.model.CompanyListingDomain


@Composable fun CompanyItem(
    modifier: Modifier,
    company: CompanyListingDomain
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
       Column(
           modifier = Modifier.weight(1f)
       ) {

           Row(
               modifier = Modifier.fillMaxWidth()
           ) {
               Text(
                   text = company.name,
                   fontWeight = FontWeight.Bold,
                   fontSize = 16.sp,
                   color = MaterialTheme.colors.onBackground,
                   overflow = TextOverflow.Ellipsis,
                   maxLines = 1,
                   modifier = Modifier.weight(1f)
               )
               Spacer(modifier = Modifier.width(Dp(16f)))
               Text(
                   text = company.exchange,
                   fontWeight = FontWeight.Light,
                   fontSize = 16.sp,
                   color = MaterialTheme.colors.onBackground,
                   maxLines = 1
               )
           }

           Spacer(modifier = Modifier.width(Dp(16f)))
           Text(
               text = "(${company.symbol})",
               fontWeight = FontWeight.Light,
               fontSize = 16.sp,
               color = MaterialTheme.colors.onBackground,
               maxLines = 1,
               textAlign = TextAlign.Start,
               modifier = Modifier.padding(
                   start = Dp(16f),
                   top = Dp(8f),
                   bottom = Dp(6f)
               )
           )

       }
    }
}

@Preview
@Composable
fun ItemPreview(){
    Box {
        CompanyItem(
            modifier = Modifier.padding(16.dp),
            company = CompanyListingDomain(
                name = "Teste 1",
                exchange = "$",
                symbol = "$"
            )
        )
    }
}