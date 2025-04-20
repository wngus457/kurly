package com.juhyeon.kurly.feature.home

import com.juhyeon.kurly.shared.core.mvi.UiEffect
import com.juhyeon.kurly.shared.core.mvi.UiEvent
import com.juhyeon.kurly.shared.core.mvi.UiState
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductItem
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModel
import okhttp3.internal.connection.RouteSelector

interface HomeContract {
    sealed interface Event : UiEvent {

    }
    data class State(
        val sections: Set<SectionUiModel>
    ) : UiState

    sealed interface Effect : UiEffect {

    }
}

data class SectionUiModel(
    val section: SectionList.Section,
    val productList: List<ProductItemUiModel>
)