package com.example.zencar_test.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.zencar_test.data.local_source.datastore.ZenCarStorage
import com.example.zencar_test.ui.screen.home.navigation.HomeNavigation
import com.example.zencar_test.ui.screen.home.navigation.home
import com.example.zencar_test.ui.screen.loading.LoadingScreen
import com.example.zencar_test.ui.screen.loading.navigation.LoadingNavigation
import com.example.zencar_test.ui.screen.loading.navigation.loading
import com.example.zencar_test.ui.screen.login.navigation.LoginNavigation
import com.example.zencar_test.ui.screen.login.navigation.login
import com.example.zencar_test.ui.screen.registration.navigation.registration

@Composable
fun ZenCarNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onNavigationToDestination: (route: String) -> Unit,
    onClickBack: () -> Unit,
    storage: ZenCarStorage,
) {
    val isLoggedIn by storage.isLoggedIn.collectAsStateWithLifecycle(initialValue = null)

    val startDestination = when (isLoggedIn) {
        null -> {
            LoadingNavigation.route
        }

        true -> {
            HomeNavigation.route
        }

        false -> {
            LoginNavigation.route
        }
    }

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        home(
            navigateTo = onNavigationToDestination,
            navController = navHostController,
        )
        login(
            navigateTo = onNavigationToDestination,
            navController = navHostController,
        )
        registration(
            onClickBack = onClickBack,
            navigateTo = onNavigationToDestination,
        )
        loading()
    }
}



const val DURATION_NAVIGATION_ANIMATION = 250

fun AnimatedContentTransitionScope<NavBackStackEntry>.currentRout() = initialState.destination.route

fun AnimatedContentTransitionScope<NavBackStackEntry>.targetRout() = targetState.destination.route

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainer(direction: Direction): EnterTransition {
    val slideDirection = when (direction) {
        Direction.Right -> AnimatedContentTransitionScope.SlideDirection.Right
        Direction.Left -> AnimatedContentTransitionScope.SlideDirection.Left
        Direction.Up -> AnimatedContentTransitionScope.SlideDirection.Up
        Direction.Down -> AnimatedContentTransitionScope.SlideDirection.Down
    }
    return slideIntoContainer(
        slideDirection,
        animationSpec = tween(
            durationMillis = DURATION_NAVIGATION_ANIMATION,
            easing = EaseInOutQuad
        )
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainer(direction: Direction): ExitTransition {
    val slideDirection = when (direction) {
        Direction.Right -> AnimatedContentTransitionScope.SlideDirection.Right
        Direction.Left -> AnimatedContentTransitionScope.SlideDirection.Left
        Direction.Up -> AnimatedContentTransitionScope.SlideDirection.Up
        Direction.Down -> AnimatedContentTransitionScope.SlideDirection.Down
    }
    return slideOutOfContainer(
        slideDirection,
        animationSpec = tween(
            durationMillis = DURATION_NAVIGATION_ANIMATION,
            easing = EaseInOutQuad
        )
    )
}

enum class Direction {
    Right, Left, Up, Down
}