package com.example.zencar_test.ui.screen.registration.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.zencar_test.R
import com.example.zencar_test.navigation.ZenCarNavigation
import com.example.zencar_test.ui.screen.login.navigation.LoginNavigation
import com.example.zencar_test.ui.screen.registration.RegistrationScreen
import com.example.zencar_test.ui.screen.registration.vm.RegistrationViewModel

object RegistrationNavigation: ZenCarNavigation {
    override val route = "registration_route"
}

fun NavGraphBuilder.registration(
    onClickBack: () -> Unit,
    navigateTo: (route: String) -> Unit
) {
    composable(
        route = RegistrationNavigation.route
    ) {
        val viewModel = hiltViewModel<RegistrationViewModel>()
        val context = LocalContext.current

        LaunchedEffect(key1 = viewModel) {
            viewModel.registerResult.collect { result ->
                if (result) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.registration_success), Toast.LENGTH_LONG
                    ).show()
                    navigateTo(
                        LoginNavigation.route,
                    )
                }
            }
        }

        RegistrationScreen(
            viewModel = viewModel,
            onClickBack = onClickBack,
        )

    }
}