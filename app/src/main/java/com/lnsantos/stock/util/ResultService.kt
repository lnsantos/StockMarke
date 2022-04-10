package com.lnsantos.stock.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

typealias FlowResultService<T> = Flow<ResultService<T>>

sealed class ResultService<T>(
    val data: T? = null,
    val description: String? = null
) {
    data class Loading<T>(
        val isLoading: Boolean = true
    ) : ResultService<T>(data = null, description = null)

    data class Success<T>(
        val _data: T? = null
    ) : ResultService<T>(data = _data, description = null)

    data class Failed<T>(
        val _description: String?,
        val _data: T? = null
    ) : ResultService<T>(data = _data, description = _description)
}

internal suspend fun <T> FlowCollector<ResultService<T>>.startLoading(){
    this.emit(ResultService.Loading(isLoading = true))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.stopLoading(){
    this.emit(ResultService.Loading(isLoading = false))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.emitSuccess(data: T?){
    this.emit(ResultService.Success<T>(data))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.emitFailed(description: String?, data: T?){
    this.emit(ResultService.Failed(description, data))
}