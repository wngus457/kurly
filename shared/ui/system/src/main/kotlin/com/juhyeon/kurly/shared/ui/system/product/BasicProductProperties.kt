package com.juhyeon.kurly.shared.ui.system.product

sealed interface BasicProductProperties

sealed interface ProductViewType : BasicProductProperties {
    data object Normal : ProductViewType
    data object Banner : ProductViewType
}

sealed interface ProductBookmark : BasicProductProperties {
    data object Bookmarked : ProductBookmark
    data object NotBookmarked : ProductBookmark
}