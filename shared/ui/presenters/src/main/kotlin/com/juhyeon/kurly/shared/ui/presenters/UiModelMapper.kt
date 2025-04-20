package com.juhyeon.kurly.shared.ui.presenters

interface UiModelMapper<Domain, UiModel> {
    fun toUiModel(domain: Domain): UiModel
    fun toDomain(uiModel: UiModel): Domain
}