package com.example.zencar_test.ui.screen.login.intents

import com.example.zencar_test.base.BaseViewState

data class LoginViewState(
    val name: String = "",
    val nameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
): BaseViewState
