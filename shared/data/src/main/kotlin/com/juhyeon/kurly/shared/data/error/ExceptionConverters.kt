package com.juhyeon.kurly.shared.data.error

import com.juhyeon.kurly.shared.data.ResultWrapper
import com.juhyeon.kurly.shared.domain.Result

internal fun <T, D> ResultWrapper<T>.toDomain(onMapSuccess: (T) -> D) = when (this) {
    is ResultWrapper.Success -> Result.Success(onMapSuccess(value))
    is ResultWrapper.GenericError -> Result.Error(
        exception = exception,
        message = message
    )
    is ResultWrapper.NetworkError -> Result.Error(
        exception = Exception("network error"),
        message = "Network Error"
    )
}