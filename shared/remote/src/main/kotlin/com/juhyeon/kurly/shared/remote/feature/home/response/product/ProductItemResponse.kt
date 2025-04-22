package com.juhyeon.kurly.shared.remote.feature.home.response.product

import com.juhyeon.kurly.shared.data.feature.home.product.ProductItemData
import com.squareup.moshi.Json

data class ProductItemResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "originalPrice")
    val originalPrice: Int,
    @Json(name = "discountedPrice")
    val discountedPrice: Int?,
    @Json(name = "isSoldOut")
    val isSoldOut: Boolean
)

internal fun ProductItemResponse.toData() = ProductItemData(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut
)