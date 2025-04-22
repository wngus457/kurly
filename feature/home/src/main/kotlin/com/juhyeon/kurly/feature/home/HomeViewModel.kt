package com.juhyeon.kurly.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.juhyeon.kurly.shared.core.mvi.BaseViewModel
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.GetSectionProductListUseCase
import com.juhyeon.kurly.shared.domain.feature.home.section.usecase.GetSectionListUseCase
import com.juhyeon.kurly.shared.domain.filterSuccessData
import com.juhyeon.kurly.shared.domain.onLoading
import com.juhyeon.kurly.shared.domain.onSuccess
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModelMapper
import com.juhyeon.kurly.shared.ui.presenters.section.SectionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionListUseCase: GetSectionListUseCase,
    private val getSectionProductListUseCase: GetSectionProductListUseCase,
    private val productItemUiModelMapper: ProductItemUiModelMapper
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    
    private val nextPage = mutableStateOf<Int?>(1)
    
    override fun initState() = HomeContract.State(
        uiState = HomeContract.State.HomeUiState.Loading
    )

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnRefresh -> getSectionList()
            is HomeContract.Event.OnPullToRefresh -> pullToRefresh()
        }
    }

    private fun pullToRefresh() {
        reducer.setState { copy(uiState = HomeContract.State.HomeUiState.Loading) }
        nextPage.value = 1
        getSectionList()
    }

    private fun getSectionList() {
        nextPage.value?.let { page ->
            getSectionListUseCase(page)
                .onLoading { reducer.setEffect(HomeContract.Effect.ShowLoading) }
                .onSuccess { sectionResult ->
                    nextPage.value = sectionResult.paging
                    val sections = mutableListOf<SectionUiModel>()
                    sectionResult.list.asFlow()
                        .flatMapMerge { section ->
                            getSectionProductListUseCase(section.id)
                                .filterSuccessData()
                                .map { productList ->
                                    SectionUiModel(
                                        section = section,
                                        productList = productList.map { productItemUiModelMapper.toUiModel(it) }
                                    )
                                }
                        }
                        .collect { sectionUiModel ->
                            sections.add(sectionUiModel)
                            if (sections.size == sectionResult.list.size) {
                                sections
                                    .sortedBy { it.section.id }
                                    .let { sortedSections ->
                                        val result = if (stateFlow.value.uiState is HomeContract.State.HomeUiState.Loading) {
                                            sortedSections
                                        } else {
                                            (stateFlow.value.uiState as HomeContract.State.HomeUiState.Success).sections + sortedSections
                                        }
                                        reducer.setState {
                                            copy(uiState = HomeContract.State.HomeUiState.Success(sections = result))
                                        }
                                        reducer.setEffect(HomeContract.Effect.HidePullToRefresh)
                                        reducer.setEffect(HomeContract.Effect.HideLoading)
                                    }
                            }
                        }
                }
                .launchIn(viewModelScope)
        }
    }
}