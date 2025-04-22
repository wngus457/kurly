package com.juhyeon.kurly.shared.ui.presenters.section

import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModel

data class SectionUiModel(
    val section: SectionList.Section,
    val productList: List<ProductItemUiModel>
)
