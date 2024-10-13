package com.example.zencar_test.ui.screen.home.intents

import com.example.zencar_test.base.BaseViewState
import com.example.zencar_test.domain.model.User

data class HomeViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val listUser: List<User> = emptyList(),
    val user: User = User.emptyMock,
): BaseViewState
