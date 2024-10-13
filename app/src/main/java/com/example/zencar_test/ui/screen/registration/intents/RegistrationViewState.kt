package com.example.zencar_test.ui.screen.registration.intents

import com.example.zencar_test.base.BaseViewState

data class RegistrationViewState(
    val name: String = "",
    val nameError: String? = null,
    val img: String = "",
    val birthday: String = "",
    val birthdayError: String? = null,
    val password: String = "",
    val passwordError: String? = "",
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
) : BaseViewState