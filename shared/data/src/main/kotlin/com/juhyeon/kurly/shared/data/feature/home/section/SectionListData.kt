package com.juhyeon.kurly.shared.data.feature.home.section

import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList

data class SectionListData(
    val list: List<Section>,
    val paging: Int?
) {
    data class Section(
        val title: String,
        val id: Int,
        val type: SectionTypeData,
        val url: String
    )
}

internal fun SectionListData.toDomain() = SectionList(
    list = list.map { it.toDomain() },
    paging = paging
)

private fun SectionListData.Section.toDomain() = SectionList.Section(
    title = title,
    id = id,
    type = type.toDomain(),
    url = url
)