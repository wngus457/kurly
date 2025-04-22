package com.juhyeon.kurly.shared.domain.feature.home.product.usecase

import com.juhyeon.kurly.shared.domain.FlowUseCase
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.di.DefaultDispatcher
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class GetSectionProductListUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, List<ProductItem>>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<List<ProductItem>>> {
        return homeRepository.getSectionProductList(parameters).distinctUntilChanged()
    }
}