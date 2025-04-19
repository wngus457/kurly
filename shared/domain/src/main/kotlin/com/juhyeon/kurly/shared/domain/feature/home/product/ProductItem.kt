package com.juhyeon.kurly.shared.domain.feature.home.product

data class ProductItem(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val isSoldOut: Boolean
)
