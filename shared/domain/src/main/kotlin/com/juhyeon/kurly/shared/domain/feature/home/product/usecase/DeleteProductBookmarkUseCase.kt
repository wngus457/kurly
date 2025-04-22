package com.juhyeon.kurly.shared.domain.feature.home.product.usecase

import com.juhyeon.kurly.shared.domain.FlowUseCase
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.di.DefaultDispatcher
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteProductBookmarkUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, Unit>(dispatcher) {
    override fun execute(parameters: Int): Flow<Result<Unit>> = flow {
        emit(Result.Success(homeRepository.deleteProductBookmark(parameters)))
    }
}