package com.juhyeon.kurly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.juhyeon.kurly.feature.home.HomeScreen
import com.juhyeon.kurly.shared.navigation.HomeRoute
import com.juhyeon.kurly.shared.ui.common.extension.noAnimComposable

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        noAnimComposable<HomeRoute> {
            HomeScreen(navController)
        }
    }
}