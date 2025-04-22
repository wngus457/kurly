package com.juhyeon.kurly.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
internal fun LazyItemScope.HomeSectionGridComponent(
    section: SectionUiModel,
    bookmarkList: Set<Int>,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    HomeSectionScaffold(
        title = section.section.title,
        content = {
            LazyVerticalGrid(
                modifier = Modifier
                    .heightIn(max = 600.dp)
                    .wrapContentHeight(),
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(
                    items = if (section.productList.size > 6) section.productList.subList(0, 6) else section.productList,
                    key = { _, product -> product.id }
                ) { index, product ->
                    if (index < 6) {
                        BasicProductComponent(
                            type = ProductViewType.Normal,
                            item = product,
                            bookmark = if (bookmarkList.contains(product.id)) ProductBookmark.Bookmarked else ProductBookmark.NotBookmarked,
                            onBookmarkClick = { isBookmarked -> onBookmarkClick(product.id, isBookmarked) }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeSectionGridComponentPreview() {
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
            HomeSectionGridComponent(
                section = SectionUiModel(
                    section = SectionList.Section(
                        title = "horizontalSection",
                        id = 1,
                        type = SectionType.Grid,
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