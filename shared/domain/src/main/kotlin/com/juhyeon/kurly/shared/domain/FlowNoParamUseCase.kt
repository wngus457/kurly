package com.juhyeon.kurly.shared.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowNoParamUseCase<R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(): Flow<Result<R>> = execute()
        .catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(): Flow<Result<R>>
}