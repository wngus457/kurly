package com.juhyeon.kurly.shared.domain.feature.home

import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getSectionList(pageNumber: Int): Flow<Result<SectionList>>
    fun getSectionProductList(sectionId: Int): Flow<Result<List<ProductItem>>>
}