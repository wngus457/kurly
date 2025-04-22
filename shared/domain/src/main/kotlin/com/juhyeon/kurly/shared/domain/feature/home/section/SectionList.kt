package com.juhyeon.kurly.shared.domain.feature.home.section

data class SectionList(
    val list: List<Section>,
    val paging: Int?
) {
    data class Section(
        val title: String,
        val id: Int,
        val type: SectionType,
        val url: String
    )
}
