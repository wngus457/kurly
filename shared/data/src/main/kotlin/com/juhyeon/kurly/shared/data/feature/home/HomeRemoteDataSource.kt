package com.juhyeon.kurly.shared.data.feature.home

import com.juhyeon.kurly.shared.data.ResultWrapper
import com.juhyeon.kurly.shared.data.feature.home.product.ProductItemData
import com.juhyeon.kurly.shared.data.feature.home.section.SectionListData

interface HomeRemoteDataSource {
    suspend fun getSectionList(pageNumber: Int): ResultWrapper<SectionListData>
    suspend fun getSectionProductList(sectionId: Int): ResultWrapper<List<ProductItemData>>
}