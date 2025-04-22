package com.juhyeon.kurly.shared.data.feature.home.product

import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem

data class ProductItemData(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val isSoldOut: Boolean
)

internal fun ProductItemData.toDomain() = ProductItem(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut
)