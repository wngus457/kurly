package com.juhyeon.kurly.shared.remote

import com.juhyeon.kurly.shared.data.ResultWrapper
import com.juhyeon.kurly.shared.util.android.LogHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    logHelper: LogHelper,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    ResultWrapper.GenericError(
                        exception = throwable,
                        message = throwable.message ?: "일시적인 오류가 발생했습니다.",
                    )
                }
                else -> {
                    ResultWrapper.GenericError(
                        exception = Exception("default error"),
                        message = "일시적인 오류가 발생했습니다.",
                    )
                }
            }
        }
    }
}