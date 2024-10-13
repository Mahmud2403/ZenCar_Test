package com.example.zencar_test.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.zencar_test.data.local_source.datastore.ZenCarStorage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appState: FoodiesAppState,
    storage: ZenCarStorage,
) {
    Scaffold(
        modifier = modifier
            .navigationBarsPadding(),
        containerColor = Color.White,
    ) {
        ZenCarNavHost(
            navHostController = appState.navController,
            onNavigationToDestination = appState::navigate,
            onClickBack = appState::onBackClick,
            storage = storage,
        )
    }
}

