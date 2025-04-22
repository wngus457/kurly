package com.juhyeon.kurly.feature.home

import com.juhyeon.kurly.shared.core.mvi.UiEffect
import com.juhyeon.kurly.shared.core.mvi.UiEvent
import com.juhyeon.kurly.shared.core.mvi.UiState
import com.juhyeon.kurly.shared.ui.presenters.section.SectionUiModel

interface HomeContract {
    sealed interface Event : UiEvent {
        data object OnRefresh : Event
        data object OnPullToRefresh : Event
    }
    data class State(
        val uiState: HomeUiState = HomeUiState.Loading
    ) : UiState {
        sealed interface HomeUiState {
            data object Loading : HomeUiState
            data class Success(val sections: List<SectionUiModel> = emptyList()) : HomeUiState
        }
    }

    sealed interface Effect : UiEffect {
        data object ShowLoading : Effect
        data object HideLoading : Effect
        data object HidePullToRefresh : Effect
    }
}