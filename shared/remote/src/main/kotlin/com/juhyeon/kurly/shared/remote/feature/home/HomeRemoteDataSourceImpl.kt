package com.juhyeon.kurly.shared.remote.feature.home

import com.juhyeon.kurly.shared.data.ResultWrapper
import com.juhyeon.kurly.shared.data.feature.home.HomeRemoteDataSource
import com.juhyeon.kurly.shared.data.feature.home.product.ProductItemData
import com.juhyeon.kurly.shared.data.feature.home.section.SectionListData
import com.juhyeon.kurly.shared.remote.feature.home.response.product.toData
import com.juhyeon.kurly.shared.remote.feature.home.response.toData
import com.juhyeon.kurly.shared.remote.safeApiCall
import com.juhyeon.kurly.shared.remote.service.HomeService
import com.juhyeon.kurly.shared.util.android.LogHelper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val logHelper: LogHelper,
    private val homeService: HomeService
) : HomeRemoteDataSource {

    override suspend fun getSectionList(pageNumber: Int): ResultWrapper<SectionListData> {
        val response = homeService.getSectionList(page = pageNumber)
        return safeApiCall(Dispatchers.IO, logHelper) {
            response.toData()
        }
    }

    override suspend fun getSectionProductList(sectionId: Int): ResultWrapper<List<ProductItemData>> {
        val response = homeService.getSectionProductList(sectionId = sectionId)
        return safeApiCall(Dispatchers.IO, logHelper) {
            response.productList.map { it.toData() }
        }
    }
}