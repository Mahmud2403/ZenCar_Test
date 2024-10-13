package com.example.zencar_test.ui.screen.loading.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.zencar_test.navigation.ZenCarNavigation
import com.example.zencar_test.ui.screen.loading.LoadingScreen


object LoadingNavigation : ZenCarNavigation {
    override val route = "loading_route"
}

fun NavGraphBuilder.loading() {
    composable(
        route = LoadingNavigation.route
    ) {
        LoadingScreen()
    }
}