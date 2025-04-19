package com.juhyeon.kurly.shared.data.feature.home.section

import com.juhyeon.kurly.shared.domain.feature.home.section.SectionType

sealed interface SectionTypeData {
    data object Grid : SectionTypeData
    data object Vertical : SectionTypeData
    data object Horizontal : SectionTypeData
    data object None : SectionTypeData
}

internal fun SectionTypeData.toDomain() = when (this) {
    SectionTypeData.Grid -> SectionType.Grid
    SectionTypeData.Vertical -> SectionType.Vertical
    SectionTypeData.Horizontal -> SectionType.Horizontal
    SectionTypeData.None -> SectionType.None
}