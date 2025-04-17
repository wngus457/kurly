package com.juhyeon.kurly.feature.home

import com.juhyeon.kurly.shared.core.mvi.UiEffect
import com.juhyeon.kurly.shared.core.mvi.UiEvent
import com.juhyeon.kurly.shared.core.mvi.UiState

interface HomeContract {
    sealed interface Event : UiEvent {

    }
    data object State : UiState

    sealed interface Effect : UiEffect {

    }
}