package com.juhyeon.kurly.shared.ui.presenters.product

import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem
import com.juhyeon.kurly.shared.ui.presenters.UiModelMapper
import javax.inject.Inject

class ProductItemUiModelMapper @Inject constructor() : UiModelMapper<ProductItem, ProductItemUiModel> {
    override fun toUiModel(domain: ProductItem) = ProductItemUiModel(
        id = domain.id,
        name = domain.name,
        image = domain.image,
        price = ProductItemUiModel.ProductPrice(
            originalPrice = domain.originalPrice,
            discountedPrice = domain.discountedPrice
        ),
        isSoldOut = domain.isSoldOut
    )

    override fun toDomain(uiModel: ProductItemUiModel) = ProductItem(
        id = uiModel.id,
        name = uiModel.name,
        image = uiModel.image,
        originalPrice = uiModel.price.originalPrice,
        discountedPrice = uiModel.price.discountedPrice,
        isSoldOut = uiModel.isSoldOut
    )
}