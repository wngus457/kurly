package com.juhyeon.kurly.shared.util.kotlin.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime > windowDuration) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}