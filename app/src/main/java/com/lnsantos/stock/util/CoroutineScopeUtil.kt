package com.lnsantos.stock.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun CoroutineScope.launchWithDelay(time: Long, onRunning: () -> Unit) = launch {
    delay(time)
    onRunning()
}