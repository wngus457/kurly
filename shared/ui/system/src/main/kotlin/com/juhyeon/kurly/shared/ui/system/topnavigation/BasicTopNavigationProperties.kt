package com.juhyeon.kurly.shared.ui.system.topnavigation

sealed interface BasicTopNavigationProperties

sealed interface TopNavigationTitle : BasicTopNavigationProperties {
    data class On(val title: String) : TopNavigationTitle
    data object Off : TopNavigationTitle
}