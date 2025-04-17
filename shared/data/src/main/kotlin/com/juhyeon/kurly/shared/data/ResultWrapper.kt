package com.juhyeon.kurly.shared.data

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(
        val message: String,
        val exception: Exception?
    ) : ResultWrapper<Nothing>()
    data object NetworkError : ResultWrapper<Nothing>()
}