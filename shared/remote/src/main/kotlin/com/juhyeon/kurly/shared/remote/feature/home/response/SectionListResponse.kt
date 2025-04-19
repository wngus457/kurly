package com.juhyeon.kurly.shared.remote.feature.home.response

import com.juhyeon.kurly.shared.data.feature.home.section.SectionListData
import com.juhyeon.kurly.shared.data.feature.home.section.SectionTypeData
import com.squareup.moshi.Json

data class SectionListResponse(
    @Json(name = "data")
    val list: List<Section>,
    @Json(name = "paging")
    val paging: Paging?
) {
    data class Section(
        @Json(name = "title")
        val title: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "type")
        val type: String,
        @Json(name = "url")
        val url: String
    )
    data class Paging(
        @Json(name = "next_page")
        val nextPage: Int
    )
}

internal fun SectionListResponse.toData() = SectionListData(
    list = list.map { it.toData() },
    paging = paging?.nextPage
)

private fun SectionListResponse.Section.toData() = SectionListData.Section(
    title = title,
    id = id,
    type = type.toSectionType(),
    url = url
)

private fun String.toSectionType() = when (this) {
    "vertical" -> SectionTypeData.Vertical
    "horizontal" -> SectionTypeData.Horizontal
    "grid" -> SectionTypeData.Grid
    else -> SectionTypeData.None
}