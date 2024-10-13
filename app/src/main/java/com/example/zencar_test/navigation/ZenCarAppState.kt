package com.example.zencar_test.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): FoodiesAppState {
    return remember(navController) {
        FoodiesAppState(navController)
    }
}

@Stable
class FoodiesAppState(
    val navController: NavHostController,
) {

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}
