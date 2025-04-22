package com.juhyeon.kurly.shared.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juhyeon.kurly.shared.data.feature.home.product.ProductBookmarkData

@Entity(tableName = "bookmark")
data class ProductBookmarkEntity(
    @PrimaryKey
    val id: Int,
    val dateTime: String
)

internal fun ProductBookmarkData.toLocal() = ProductBookmarkEntity(
    id = id,
    dateTime = dateTime
)

internal fun ProductBookmarkEntity.toData() = ProductBookmarkData(
    id = id,
    dateTime = dateTime
)
