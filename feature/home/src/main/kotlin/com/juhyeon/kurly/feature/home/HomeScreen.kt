package com.juhyeon.kurly.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.stateFlow.collectAsState().value
    val eventHandler = homeViewModel.eventHandler

    LaunchedEffect(true) {
        homeViewModel.effectFlow.collect { effect ->
            when (effect) {
                else -> { }
            }
        }
    }

    HomeContents()
}

@Composable
private fun HomeContents(

) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Home"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentsPreview() {
    HomeContents()
}