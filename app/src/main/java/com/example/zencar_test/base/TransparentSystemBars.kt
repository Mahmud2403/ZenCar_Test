package com.example.zencar_test.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.zencar_test.ui.theme.PrimaryCyan
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val BlackScrim = Color(0f, 0f, 0f, 1f)

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = PrimaryCyan,
            isNavigationBarContrastEnforced = false,
            darkIcons = false,
            transformColorForLightContent = {
                BlackScrim
            }
        )
        systemUiController.setNavigationBarColor(Color.Black)

        onDispose {  }
    }
}