package com.juhyeon.kurly.shared.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {
    private val lazyInitState by lazy { initState() }
    val reducer = MviReducer<Event, State, Effect>(
            viewModelScope = viewModelScope,
            initialState = lazyInitState,
            handleEvent = ::handleEvent
        )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    abstract fun initState(): State

    abstract fun handleEvent(event: Event)
}