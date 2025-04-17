package com.juhyeon.kurly.feature.home

import com.juhyeon.kurly.shared.core.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    override fun initState() = HomeContract.State

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            else -> { }
        }
    }
}