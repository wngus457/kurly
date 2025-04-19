package com.juhyeon.kurly.shared.remote.feature.home.response

import com.juhyeon.kurly.shared.remote.feature.home.response.product.ProductItemResponse
import com.squareup.moshi.Json

data class SectionProductListResponse(
    @Json(name= "data")
    val productList: List<ProductItemResponse>
)
