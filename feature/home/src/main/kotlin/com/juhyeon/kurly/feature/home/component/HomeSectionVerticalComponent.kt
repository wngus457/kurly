package com.juhyeon.kurly.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionType
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModel
import com.juhyeon.kurly.shared.ui.presenters.section.SectionUiModel
import com.juhyeon.kurly.shared.ui.system.product.BasicProductComponent
import com.juhyeon.kurly.shared.ui.system.product.ProductBookmark
import com.juhyeon.kurly.shared.ui.system.product.ProductViewType

@Composable
internal fun LazyItemScope.HomeSectionVerticalComponent(
    section: SectionUiModel
) {
    HomeSectionScaffold(
        title = section.section.title,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                section.productList.forEach { product ->
                    BasicProductComponent(
                        type = ProductViewType.Banner,
                        item = product,
                        bookmark = ProductBookmark.Bookmarked
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeSectionVerticalComponentPreview() {
    val list = mutableListOf<ProductItemUiModel>()
    (1..10).forEach { i ->
        list.add(
            ProductItemUiModel(
                id = i,
                name = "테스트$i",
                image = "",
                price = ProductItemUiModel.ProductPrice(
                    originalPrice = 10000,
                    discountedPrice = 8000,
                ),
                isSoldOut = false
            )
        )
    }
    LazyColumn {
        item {
            HomeSectionVerticalComponent(
                section = SectionUiModel(
                    section = SectionList.Section(
                        title = "horizontalSection",
                        id = 1,
                        type = SectionType.Horizontal,
                        url = ""
                    ),
                    productList = list
                )
            )
        }
    }
}