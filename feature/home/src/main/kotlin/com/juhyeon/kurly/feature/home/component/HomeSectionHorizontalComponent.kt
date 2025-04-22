package com.juhyeon.kurly.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
internal fun LazyItemScope.HomeSectionHorizontalComponent(
    section: SectionUiModel,
    bookmarkList: Set<Int>,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    HomeSectionScaffold(
        title = section.section.title,
        content = {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(
                    items = section.productList,
                    key = { product -> product.id }
                ) { product ->
                    BasicProductComponent(
                        type = ProductViewType.Normal,
                        item = product,
                        bookmark = if (bookmarkList.contains(product.id)) ProductBookmark.Bookmarked else ProductBookmark.NotBookmarked,
                        onBookmarkClick = { isBookmarked -> onBookmarkClick(product.id, isBookmarked) }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeSectionHorizontalComponentPreview() {
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
            HomeSectionHorizontalComponent(
                section = SectionUiModel(
                    section = SectionList.Section(
                        title = "horizontalSection",
                        id = 1,
                        type = SectionType.Horizontal,
                        url = ""
                    ),
                    productList = list
                ),
                bookmarkList = setOf(),
                onBookmarkClick = { _, _ -> }
            )
        }
    }
}