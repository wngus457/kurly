package com.juhyeon.kurly.shared.ui.system.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModel
import com.juhyeon.kurly.shared.ui.system.icon.CommonHeartOff
import com.juhyeon.kurly.shared.ui.system.icon.CommonHeartOn
import com.juhyeon.kurly.shared.ui.system.theme.Black800
import com.juhyeon.kurly.shared.ui.system.theme.Gray550
import com.juhyeon.kurly.shared.ui.system.theme.Red450
import com.juhyeon.kurly.shared.ui.system.theme.medium
import com.juhyeon.kurly.shared.ui.system.theme.normal
import com.juhyeon.kurly.shared.ui.system.theme.semiBold
import com.juhyeon.kurly.shared.util.kotlin.extension.applyCommaFormat
import com.juhyeon.kurly.shared.util.kotlin.extension.toSalePercent

@Composable
fun BasicProductComponent(
    type: ProductViewType,
    item: ProductItemUiModel,
    bookmark: ProductBookmark = ProductBookmark.NotBookmarked
) {
    val width = when (type) {
        ProductViewType.Normal -> Modifier.width(150.dp)
        ProductViewType.Banner -> Modifier.fillMaxWidth()
    }

    Column(
        modifier = Modifier.then(width),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ProductImage(
            imageUrl = { item.image },
            type = type,
            bookmark = bookmark
        )

        Text(
            text = item.name,
            style = MaterialTheme.typography.normal(16),
            maxLines = if (type == ProductViewType.Normal) 2 else 1,
            color = Black800,
            overflow = TextOverflow.Ellipsis
        )

        ProductPrice(
            type = type,
            price = item.price
        )
    }
}

@Composable
private fun ProductImage(
    type: ProductViewType,
    imageUrl: () -> String,
    bookmark: ProductBookmark
) {
    val height = when (type) {
        ProductViewType.Normal -> Modifier.height(200.dp)
        ProductViewType.Banner -> Modifier.aspectRatio(6 / 4f)
    }

    Box(
        modifier = Modifier
            .then(height)
    ) {
        AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = "goods image"
        )

        Image(
            modifier = Modifier.align(Alignment.TopEnd),
            painter = painterResource(if (bookmark is ProductBookmark.Bookmarked) CommonHeartOn else CommonHeartOff),
            contentDescription = ""
        )
    }
}

@Composable
private fun ProductPrice(
    type: ProductViewType,
    price: ProductItemUiModel.ProductPrice
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            price.discountedPrice?.let { discount ->
                Text(
                    text = "${discount.toSalePercent(price.originalPrice)}%",
                    style = MaterialTheme.typography.medium(14),
                    color = Red450
                )
                Text(
                    text = "${discount.applyCommaFormat()}원",
                    style = MaterialTheme.typography.semiBold(14),
                    color = Black800
                )
            }
            if (price.discountedPrice == null) {
                Text(
                    text = "${price.originalPrice.applyCommaFormat()}원",
                    style = MaterialTheme.typography.semiBold(14),
                    color = Black800
                )
            }

            if (type == ProductViewType.Banner && price.discountedPrice != null) {
                Text(
                    text = "${price.originalPrice.applyCommaFormat()}원",
                    style = MaterialTheme.typography.normal(12),
                    color = Gray550,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
        if (type == ProductViewType.Normal && price.discountedPrice != null) {
            Text(
                text = "${price.originalPrice.applyCommaFormat()}원",
                style = MaterialTheme.typography.normal(12),
                color = Gray550,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicProductComponentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            BasicProductComponent(
                type = ProductViewType.Normal,
                item = ProductItemUiModel(
                    id = 0,
                    name = "상품명상품명상품명상품명상품명상품명상품명상품명상품명",
                    image = "",
                    price = ProductItemUiModel.ProductPrice(
                        originalPrice = 10000,
                        discountedPrice = 7000
                    ),
                    isSoldOut = false
                ),
                bookmark = ProductBookmark.Bookmarked
            )
            BasicProductComponent(
                type = ProductViewType.Normal,
                item = ProductItemUiModel(
                    id = 0,
                    name = "상품명상품명상품명상품명상품명상품명상품명상품명상품명",
                    image = "",
                    price = ProductItemUiModel.ProductPrice(
                        originalPrice = 10000,
                        discountedPrice = null
                    ),
                    isSoldOut = false
                )
            )
        }
        BasicProductComponent(
            type = ProductViewType.Banner,
            item = ProductItemUiModel(
                id = 0,
                name = "상품명상품명상품명상품명상품명상품명상품명상품명상품명상품명",
                image = "",
                price = ProductItemUiModel.ProductPrice(
                    originalPrice = 10000,
                    discountedPrice = 8000
                ),
                isSoldOut = false
            )
        )
    }
}