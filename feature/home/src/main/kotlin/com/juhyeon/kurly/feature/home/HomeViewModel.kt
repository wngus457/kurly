package com.juhyeon.kurly.feature.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.juhyeon.kurly.shared.core.mvi.BaseViewModel
import com.juhyeon.kurly.shared.domain.feature.home.product.ProductBookmark
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.DeleteProductBookmarkUseCase
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.GetProductBookmarkListUseCase
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.GetSectionProductListUseCase
import com.juhyeon.kurly.shared.domain.feature.home.product.usecase.SetProductBookmarkUseCase
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionListUseCase: GetSectionListUseCase,
    private val getSectionProductListUseCase: GetSectionProductListUseCase,
    private val productItemUiModelMapper: ProductItemUiModelMapper,
    private val setProductBookmarkUseCase: SetProductBookmarkUseCase,
    private val getProductBookmarkListUseCase: GetProductBookmarkListUseCase,
    private val deleteProductBookmarkUseCase: DeleteProductBookmarkUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    private val nextPage = mutableStateOf<Int?>(1)
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
    private var lastBackPressedTimeMillis = 0L

    override fun initState() = HomeContract.State(
        uiState = HomeContract.State.HomeUiState.Loading
    )

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnGetContents -> getSectionList()
            is HomeContract.Event.OnPullToRefresh -> pullToRefresh()
            is HomeContract.Event.OnBookmarkClick -> {
                if (event.isBookmark) setBookmark(event.id)
                else deleteBookmark(event.id)
            }
            is HomeContract.Event.OnBackClick -> onBackPressed()
        }
    }

    init {
        getBookmarkList()
    }

    private fun pullToRefresh() {
        reducer.setState { copy(uiState = HomeContract.State.HomeUiState.Loading) }
        nextPage.value = 1
        getSectionList()
    }

    private fun getSectionList() {
        nextPage.value?.let { page ->
            Log.e("테스트", page.toString())
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
                                        reducer.setEffect(HomeContract.Effect.HideLoading)
                                        reducer.setEffect(HomeContract.Effect.HidePullToRefresh)
                                    }
                            }
                        }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun setBookmark(productId: Int) {
        setProductBookmarkUseCase(
            ProductBookmark(
                id = productId,
                dateTime = LocalDateTime.now().format(dateFormatter)
            )
        )
            .onSuccess { getBookmarkList() }
            .launchIn(viewModelScope)
    }

    private fun deleteBookmark(productId: Int) {
        deleteProductBookmarkUseCase(productId)
            .onSuccess { getBookmarkList() }
            .launchIn(viewModelScope)
    }

    private fun getBookmarkList() {
        getProductBookmarkListUseCase()
            .onSuccess { list ->
                reducer.setState { copy(bookmarkList = (list.map { it.id }).toSet()) }
            }
            .launchIn(viewModelScope)
    }

    private fun onBackPressed() {
        closeScreenOrSaveBackPressedTime()
    }

    private fun closeScreenOrSaveBackPressedTime() {
        if (getMillisFromLastBackPressedTime() <= MILLIS_OF_CLOSING_SCREEN) {
            reducer.setEffect(HomeContract.Effect.NavigateToFinish)
        } else {
            lastBackPressedTimeMillis = System.currentTimeMillis()
            reducer.setEffect(HomeContract.Effect.ShowSnackBar(MESSAGE_CLOSE_SCREEN))
        }
    }

    private fun getMillisFromLastBackPressedTime() = System.currentTimeMillis() - lastBackPressedTimeMillis

    companion object {
        private const val MILLIS_OF_CLOSING_SCREEN = 2000L
        private const val MESSAGE_CLOSE_SCREEN = "'뒤로'버튼을 한번 더 누르면 종료합니다."
    }
}