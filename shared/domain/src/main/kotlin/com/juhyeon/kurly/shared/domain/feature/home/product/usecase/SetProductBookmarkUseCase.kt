package com.juhyeon.kurly.shared.domain.feature.home.product.usecase

import com.juhyeon.kurly.shared.domain.FlowUseCase
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.di.DefaultDispatcher
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductBookmark
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetProductBookmarkUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<ProductBookmark, Unit>(dispatcher) {

    override fun execute(parameters: ProductBookmark): Flow<Result<Unit>> = flow {
        emit(Result.Success(homeRepository.setProductBookmark(parameters)))
    }
}