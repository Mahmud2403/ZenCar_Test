package com.example.zencar_test.ui.screen.login.intents

import com.example.zencar_test.base.BaseViewIntent

sealed class LoginViewIntent: BaseViewIntent {
    data class OnChangeName(val name: String): LoginViewIntent()
    data class OnChangePassword(val password: String): LoginViewIntent()

    object OnClickLogin: LoginViewIntent()
    object OnClickRegistration: LoginViewIntent()
}