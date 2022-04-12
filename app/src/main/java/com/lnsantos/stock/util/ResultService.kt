package com.lnsantos.stock.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

typealias FlowResultService<T> = Flow<ResultService<T>>

suspend fun <T> FlowResultService<T>.isLoading(action: (isLoading: Boolean) -> Unit): FlowResultService<T> {
    collect {
        if (it is ResultService.Loading) {
            action(it.isLoading)
        }
    }
    return this
}

suspend fun <T> FlowResultService<T>.isSuccess(action: (data: T?) -> Unit): FlowResultService<T> {
    collect {
        if (it is ResultService.Success) {
            action(it._data)
        }
    }
    return this
}

suspend fun <T> FlowResultService<T>.isFailed(action: (description: String?) -> Unit): FlowResultService<T> {
    collect {
        if (it is ResultService.Failed) {
             action(it.description)
        }
    }
    return this
}

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

internal suspend fun <T> FlowCollector<ResultService<T>>.startLoading() {
    this.emit(ResultService.Loading(isLoading = true))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.stopLoading() {
    this.emit(ResultService.Loading(isLoading = false))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.emitSuccess(data: T?) {
    this.emit(ResultService.Success<T>(data))
}

internal suspend fun <T> FlowCollector<ResultService<T>>.emitFailed(
    description: String?,
    data: T?
) {
    this.emit(ResultService.Failed(description, data))
}