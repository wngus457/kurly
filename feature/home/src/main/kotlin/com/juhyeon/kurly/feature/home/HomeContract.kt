package com.juhyeon.kurly.feature.home

import com.juhyeon.kurly.shared.core.mvi.UiEffect
import com.juhyeon.kurly.shared.core.mvi.UiEvent
import com.juhyeon.kurly.shared.core.mvi.UiState
import com.juhyeon.kurly.shared.ui.presenters.section.SectionUiModel

interface HomeContract {
    sealed interface Event : UiEvent {
        data object OnGetContents : Event
        data object OnPullToRefresh : Event
        data class OnBookmarkClick(val id: Int, val isBookmark: Boolean) : Event
        data object OnBackClick : Event
    }
    data class State(
        val uiState: HomeUiState = HomeUiState.Loading,
        val bookmarkList: Set<Int> = emptySet()
    ) : UiState {
        sealed interface HomeUiState {
            data object Loading : HomeUiState
            data class Success(val sections: List<SectionUiModel> = emptyList()) : HomeUiState
        }
    }

    sealed interface Effect : UiEffect {
        data object NavigateToFinish : Effect
        data object ShowLoading : Effect
        data object HideLoading : Effect
        data object HidePullToRefresh : Effect
        data class ShowSnackBar(val message: String) : Effect
    }
}