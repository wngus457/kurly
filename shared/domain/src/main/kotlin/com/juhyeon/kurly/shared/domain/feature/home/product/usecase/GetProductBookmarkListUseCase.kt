package com.juhyeon.kurly.shared.domain.feature.home.product.usecase

import com.juhyeon.kurly.shared.domain.FlowNoParamUseCase
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.di.DefaultDispatcher
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductBookmark
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class GetProductBookmarkListUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowNoParamUseCase<List<ProductBookmark>>(dispatcher) {

    override fun execute(): Flow<Result<List<ProductBookmark>>> {
        return homeRepository.getProductBookmarkList().take(1)
    }
}