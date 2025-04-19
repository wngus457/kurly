package com.juhyeon.kurly.shared.remote.service

import com.juhyeon.kurly.shared.remote.feature.home.response.SectionListResponse
import com.juhyeon.kurly.shared.remote.feature.home.response.SectionProductListResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService {

    @POST("sections")
    suspend fun getSectionList(
        @Query("page") page: Int
    ): SectionListResponse

    @POST("section/products")
    suspend fun getSectionProductList(
        @Query("sectionId") sectionId: Int
    ): SectionProductListResponse
}