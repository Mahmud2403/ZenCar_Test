package com.example.zencar_test.ui.screen.home.intents

import com.example.zencar_test.base.BaseViewIntent

sealed class HomeViewIntent: BaseViewIntent {
    data class OnDeleteUser(val id: Int): HomeViewIntent()

    object OnLogout: HomeViewIntent()
    object ToggleTheme: HomeViewIntent()
}