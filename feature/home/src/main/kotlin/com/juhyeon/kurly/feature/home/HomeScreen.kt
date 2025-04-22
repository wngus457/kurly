package com.juhyeon.kurly.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.juhyeon.kurly.feature.home.component.HomeSectionGridComponent
import com.juhyeon.kurly.feature.home.component.HomeSectionHorizontalComponent
import com.juhyeon.kurly.feature.home.component.HomeSectionVerticalComponent
import com.juhyeon.kurly.feature.home.component.HomeShimmerComponent
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionType
import com.juhyeon.kurly.shared.ui.common.extension.OnLifecycleEvent
import com.juhyeon.kurly.shared.ui.presenters.product.ProductItemUiModel
import com.juhyeon.kurly.shared.ui.presenters.section.SectionUiModel
import com.juhyeon.kurly.shared.ui.system.animation.ProgressIndicator
import com.juhyeon.kurly.shared.ui.system.snackbar.SnackBar
import com.juhyeon.kurly.shared.ui.system.theme.Gray300
import com.juhyeon.kurly.shared.ui.system.topnavigation.BasicTopNavigationBar
import com.juhyeon.kurly.shared.ui.system.topnavigation.TopNavigationTitle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.stateFlow.collectAsState().value
    val eventHandler = homeViewModel.eventHandler
    val lazyColumnState = rememberLazyListState()
    val bottomScrollLoadingVisible = remember { mutableStateOf(false) }
    val refreshState = remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        homeViewModel.effectFlow.collect { effect ->
            when (effect) {
                is HomeContract.Effect.NavigateToFinish -> { scope.launch { activity?.finish() } }
                is HomeContract.Effect.ShowLoading -> bottomScrollLoadingVisible.value = true
                is HomeContract.Effect.HideLoading -> bottomScrollLoadingVisible.value = false
                is HomeContract.Effect.HidePullToRefresh -> refreshState.value = false
                is HomeContract.Effect.ShowSnackBar -> {
                    scope.launch {
                        val job = launch {
                            snackBarState.showSnackbar(effect.message)
                        }
                        delay(2500L)
                        job.cancel()
                    }
                }
            }
        }
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> eventHandler(HomeContract.Event.OnGetContents)
            else -> { }
        }
    }

    BackHandler(enabled = true) {
        eventHandler(HomeContract.Event.OnBackClick)
    }

    HomeContents(
        state = state.uiState,
        bookmarkList = state.bookmarkList,
        lazyColumnState = lazyColumnState,
        snackBarState = snackBarState,
        refreshState = refreshState.value,
        bottomScrollLoadingVisible = bottomScrollLoadingVisible.value,
        onGetContents = { eventHandler(HomeContract.Event.OnGetContents) },
        onPullToRefresh = {
            if (!refreshState.value && !bottomScrollLoadingVisible.value) {
                refreshState.value = true
                eventHandler(HomeContract.Event.OnPullToRefresh)
            }
        },
        onBookmarkClick = { id, isBookmark -> eventHandler(HomeContract.Event.OnBookmarkClick(id, isBookmark)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContents(
    state: HomeContract.State.HomeUiState,
    bookmarkList: Set<Int>,
    lazyColumnState: LazyListState,
    snackBarState: SnackbarHostState,
    refreshState: Boolean,
    bottomScrollLoadingVisible: Boolean,
    onGetContents: () -> Unit,
    onPullToRefresh: () -> Unit,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackBar(
                hostState = snackBarState
            )
        }
    ) {
        PullToRefreshBox(
            isRefreshing = refreshState,
            onRefresh = onPullToRefresh
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                BasicTopNavigationBar(
                    title = TopNavigationTitle.On("컬리 과제")
                )
                when (state) {
                    is HomeContract.State.HomeUiState.Loading -> { HomeShimmerComponent() }
                    is HomeContract.State.HomeUiState.Success -> {
                        LaunchedEffect(lazyColumnState.firstVisibleItemIndex) {
                            if (lazyColumnState.firstVisibleItemIndex == (state.sections.size - 3) && !bottomScrollLoadingVisible) {
                                onGetContents()
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyColumnState,
                            contentPadding = PaddingValues(vertical = 24.dp)
                        ) {
                            itemsIndexed(
                                items = state.sections,
                                key = { _, section -> section.section.id }
                            ) { index, section ->
                                when (section.section.type) {
                                    SectionType.Horizontal -> {
                                        HomeSectionHorizontalComponent(
                                            section = section,
                                            bookmarkList = bookmarkList,
                                            onBookmarkClick = onBookmarkClick
                                        )
                                    }
                                    SectionType.Vertical -> {
                                        HomeSectionVerticalComponent(
                                            section = section,
                                            bookmarkList = bookmarkList,
                                            onBookmarkClick = onBookmarkClick
                                        )
                                    }
                                    SectionType.Grid -> {
                                        HomeSectionGridComponent(
                                            section = section,
                                            bookmarkList = bookmarkList,
                                            onBookmarkClick = onBookmarkClick
                                        )
                                    }
                                    else -> { }
                                }

                                if (index < state.sections.size - 1) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 20.dp),
                                        thickness = 4.dp,
                                        color = Gray300
                                    )
                                }
                            }
                            if (bottomScrollLoadingVisible) {
                                item { ProgressIndicator() }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentsPreview() {
    val list = mutableListOf<ProductItemUiModel>()
    (1..10).forEach { i ->
        list.add(
            ProductItemUiModel(
                id = i,
                name = "테스트$i",
                image = "",
                price = ProductItemUiModel.ProductPrice(
                    originalPrice = 10000,
                    discountedPrice = 8000,
                ),
                isSoldOut = false
            )
        )
    }
    HomeContents(
        state = HomeContract.State.HomeUiState.Success(
            sections = listOf(
                SectionUiModel(
                    section = SectionList.Section(
                        title = "verticalSection",
                        id = 1,
                        type = SectionType.Vertical,
                        url = ""
                    ),
                    productList = list
                ),
                SectionUiModel(
                    section = SectionList.Section(
                        title = "horizontalSection",
                        id = 2,
                        type = SectionType.Horizontal,
                        url = ""
                    ),
                    productList = list
                ),
                SectionUiModel(
                    section = SectionList.Section(
                        title = "gridSection",
                        id = 3,
                        type = SectionType.Grid,
                        url = ""
                    ),
                    productList = list
                )
            )
        ),
        bookmarkList = setOf(),
        lazyColumnState = rememberLazyListState(),
        snackBarState = SnackbarHostState(),
        refreshState = false,
        bottomScrollLoadingVisible = false,
        onGetContents = { },
        onPullToRefresh = { },
        onBookmarkClick = { _, _ -> }
    )
}