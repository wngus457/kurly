package com.juhyeon.kurly.shared.domain.feature.home.section

sealed interface SectionType {
    data object Grid : SectionType
    data object Vertical : SectionType
    data object Horizontal : SectionType
    data object None : SectionType
}