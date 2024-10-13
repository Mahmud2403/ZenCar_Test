package com.example.zencar_test

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import com.example.zencar_test.base.TransparentSystemBars
import com.example.zencar_test.data.local_source.datastore.ZenCarStorage
import com.example.zencar_test.navigation.AppScreen
import com.example.zencar_test.navigation.rememberAppState
import com.example.zencar_test.ui.theme.ZenCar_TestTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "zen_car_tag"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TransparentSystemBars()

            val appState = rememberAppState()
            val zenCarStorage = ZenCarStorage(context = this)

            ZenCar_TestTheme {
                AppScreen(
                    appState = appState,
                    storage = zenCarStorage,
                )
            }
        }
    }
}