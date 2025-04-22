package com.juhyeon.kurly.shared.data.feature.home.product

import com.juhyeon.kurly.shared.domain.feature.home.product.ProductBookmark

data class ProductBookmarkData(
    val id: Int,
    val dateTime: String
)

internal fun ProductBookmark.toData() = ProductBookmarkData(
    id = id,
    dateTime = dateTime
)

internal fun ProductBookmarkData.toDomain() = ProductBookmark(
    id = id,
    dateTime = dateTime
)