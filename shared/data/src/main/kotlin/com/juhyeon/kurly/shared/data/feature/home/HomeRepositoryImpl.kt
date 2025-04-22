package com.juhyeon.kurly.shared.data.feature.home

import com.juhyeon.kurly.shared.data.error.toDomain
import com.juhyeon.kurly.shared.data.feature.home.product.toData
import com.juhyeon.kurly.shared.data.feature.home.product.toDomain
import com.juhyeon.kurly.shared.data.feature.home.section.toDomain
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductBookmark
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import com.juhyeon.kurly.shared.domain.mapToResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {

    override fun getSectionList(pageNumber: Int): Flow<Result<SectionList>> = flow {
        emit(Result.Loading)
        val result = homeRemoteDataSource.getSectionList(pageNumber)
        emit(result.toDomain { it.toDomain() })
    }

    override fun getSectionProductList(sectionId: Int): Flow<Result<List<ProductItem>>> = flow {
        emit(Result.Loading)
        val result = homeRemoteDataSource.getSectionProductList(sectionId)
        emit(result.toDomain { it.map { item -> item.toDomain() } })
    }

    override suspend fun setProductBookmark(bookmark: ProductBookmark) =
        homeLocalDataSource.setProductBookmark(bookmark.toData())

    override fun getProductBookmarkList(): Flow<Result<List<ProductBookmark>>> =
        homeLocalDataSource.getProductBookmarkList()
            .map { item -> item.map { it.toDomain() } }
            .mapToResult()

    override suspend fun deleteProductBookmark(id: Int) {
        homeLocalDataSource.deleteProductBookmark(id)
    }
}