package com.juhyeon.kurly.shared.local.home

import com.juhyeon.kurly.shared.data.feature.home.HomeLocalDataSource
import com.juhyeon.kurly.shared.data.feature.home.product.ProductBookmarkData
import com.juhyeon.kurly.shared.local.product.ProductBookmarkDao
import com.juhyeon.kurly.shared.local.product.toData
import com.juhyeon.kurly.shared.local.product.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val productBookmarkDao: ProductBookmarkDao
) : HomeLocalDataSource {
    override suspend fun setProductBookmark(bookmark: ProductBookmarkData) = productBookmarkDao.setProductBookmark(bookmark.toLocal())

    override fun getProductBookmarkList(): Flow<List<ProductBookmarkData>> =
        productBookmarkDao.getProductBookmarkList().map { item -> item.map { it.toData() } }

    override suspend fun deleteProductBookmark(id: Int) = productBookmarkDao.deleteProductBookmark(id)
}