package com.example.zencar_test.ui.screen.login.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.zencar_test.R
import com.example.zencar_test.navigation.ZenCarNavigation
import com.example.zencar_test.ui.screen.home.navigation.HomeNavigation
import com.example.zencar_test.ui.screen.login.LoginScreen
import com.example.zencar_test.ui.screen.login.vm.LoginViewModel
import com.example.zencar_test.ui.screen.registration.navigation.RegistrationNavigation

object LoginNavigation : ZenCarNavigation {
    override val route = "login_route"
}

fun NavGraphBuilder.login(
    navigateTo: (route: String) -> Unit,
    navController: NavHostController,
) {
    composable(
        route = LoginNavigation.route
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val context = LocalContext.current

        LaunchedEffect(key1 = viewModel) {
            viewModel.loginResult.collect { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.registration_success), Toast.LENGTH_LONG
                    ).show()
                    navigateTo(
                        HomeNavigation.route,
                    )
                }
            }
        }

        LoginScreen(
            viewModel = viewModel,
            onClickRegistration = {
                navigateTo(RegistrationNavigation.route)
            }
        )
    }
}