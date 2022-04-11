package com.lnsantos.stock.apresentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SearchTextField(
    value: String = String(),
    setChangeValue: (String) -> Unit,
    placeholder: String = "Search..."
) {
    val modifierSettings = Modifier
        .padding(Dp(16f))
        .fillMaxWidth()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = setChangeValue,
            modifier = modifierSettings,
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            singleLine = true
        )
    }
}