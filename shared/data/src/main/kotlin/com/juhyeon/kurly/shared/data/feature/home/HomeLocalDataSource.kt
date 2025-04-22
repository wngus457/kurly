package com.juhyeon.kurly.shared.data.feature.home

import com.juhyeon.kurly.shared.data.feature.home.product.ProductBookmarkData
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    suspend fun setProductBookmark(bookmark: ProductBookmarkData)
    fun getProductBookmarkList(): Flow<List<ProductBookmarkData>>
    suspend fun deleteProductBookmark(id: Int)
}