package com.juhyeon.kurly.shared.domain

import kotlinx.coroutines.flow.*

suspend fun <R> withResult(action: suspend () -> R) = try {
    Result.Success(action.invoke())
} catch (e: Exception) {
    Result.Error(e)
}

fun <T> Flow<T>.mapToResult() = map { item ->
    if (item is Throwable) {
        Result.Error(Exception(item))
    } else {
        Result.Success(item)
    }
}

fun <T> Flow<Result<T>>.onLoading(action: suspend () -> Unit) = onEach {
    if (it is Result.Loading) {
        action.invoke()
    }
}

fun <T> Flow<Result<T>>.onSuccess(action: suspend (T) -> Unit) = onEach {
    if (it is Result.Success) {
        action.invoke(it.data)
    }
}

fun <T> Flow<Result<T>>.onErrorResult(action: suspend (Result.Error) -> Unit) = onEach {
    if (it is Result.Error) {
        action.invoke(it)
    }
}

fun <T> Flow<Result<T>>.onErrorOrCatch(action: suspend (Throwable) -> Unit) = this
    .onEach { if (it is Result.Error) action.invoke(Exception(it.exception)) }
    .catch { throwable -> action.invoke(throwable) }
