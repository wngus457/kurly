package com.juhyeon.kurly.feature.home

import androidx.lifecycle.viewModelScope
import com.juhyeon.kurly.shared.core.mvi.BaseViewModel
import com.juhyeon.kurly.shared.domain.data
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.GetSectionProductListUseCase
import com.juhyeon.kurly.shared.domain.feature.home.section.usecase.GetSectionListUseCase
import com.juhyeon.kurly.shared.domain.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionListUseCase: GetSectionListUseCase,
    private val getSectionProductListUseCase: GetSectionProductListUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    override fun initState() = HomeContract.State(
        sections = emptySet()
    )

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            else -> { }
        }
    }

    init {
        getSectionListUseCase(1)
            .onSuccess { sectionResult ->
                sectionResult.list.asFlow()
                    .flatMapMerge { section ->
                        getSectionProductListUseCase(section.id)
                            .map { productList ->
                                SectionUiModel(
                                    section = section,
                                    productList = productList.data ?: emptyList()
                                )
                            }
                    }
                    .collect { sectionResult ->
                        reducer.setState { copy(sections = stateFlow.value.sections + sectionResult) }
                    }
            }
            .launchIn(viewModelScope)
    }
}