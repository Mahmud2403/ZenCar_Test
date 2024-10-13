package com.example.zencar_test.ui.screen.home.navigation

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.zencar_test.navigation.ZenCarNavigation
import com.example.zencar_test.ui.screen.home.HomeScreen
import com.example.zencar_test.ui.screen.home.vm.HomeViewModel
import com.example.zencar_test.ui.screen.login.intents.LoginViewIntent
import com.example.zencar_test.ui.screen.login.navigation.LoginNavigation
import com.example.zencar_test.ui.screen.login.vm.LoginViewModel

object HomeNavigation : ZenCarNavigation {
    override val route = "home_route"
}

fun NavGraphBuilder.home(
    navigateTo: (route: String) -> Unit,
    navController: NavController,
) {
    composable(
        route = HomeNavigation.route,
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(HomeNavigation.route)
        }
        val viewModel = hiltViewModel<HomeViewModel>(parentEntry)
        HomeScreen(
            viewModel = viewModel,
            onClickLogout = {
                navigateTo(
                    LoginNavigation.route
                )
            }
        )
    }
}