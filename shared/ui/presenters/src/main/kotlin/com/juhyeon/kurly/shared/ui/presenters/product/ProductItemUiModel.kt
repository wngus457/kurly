package com.juhyeon.kurly.shared.ui.presenters.product

data class ProductItemUiModel(
    val id: Int,
    val name: String,
    val image: String,
    val price: ProductPrice,
    val isSoldOut: Boolean
) {
    data class ProductPrice(
        val originalPrice: Int,
        val discountedPrice: Int?
    )
}
