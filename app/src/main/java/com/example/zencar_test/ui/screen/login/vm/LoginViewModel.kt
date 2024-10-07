package com.example.zencar_test.ui.screen.login.vm

import android.content.Context
import android.util.Log
import com.example.zencar_test.R
import com.example.zencar_test.base.BaseViewModel
import com.example.zencar_test.domain.repository.LoginRepository
import com.example.zencar_test.ui.screen.login.intents.LoginViewIntent
import com.example.zencar_test.ui.screen.login.intents.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: LoginRepository,
) : BaseViewModel<LoginViewState, LoginViewIntent>(
    initialState = LoginViewState()
) {
    override fun observe(event: LoginViewIntent) {
        when (event) {
            is LoginViewIntent.OnChangeName -> {
                changeName(event.name)
            }
            is LoginViewIntent.OnChangePassword -> {
                changePassword(event.password)
            }

            LoginViewIntent.OnClickLogin -> {
                login()
            }
            LoginViewIntent.OnClickRegistration -> TODO()
        }
    }

    private fun changeName(name: String) {
        updateState {
            copy(
                name = name,
                nameError = null,
            )
        }
    }

    private fun changePassword(password: String) {
        updateState {
            copy(
                password = password,
                passwordError = null,
            )
        }
    }

    private fun login() {
        launchIOCoroutine {
            val user = repository.getUserByName(viewStateData.name)
            Log.e("LoginViewModel", "login: $user", )
            if (user != null) {
                if (user.password == viewStateData.password) {

                } else {
                    updateState {
                        copy(
                            passwordError = context.getString(R.string.validate_password_not_match)
                        )
                    }
                }
            } else {
                updateState {
                    copy(
                        nameError = context.getString(R.string.validate_name_not_match)
                    )
                }
            }
        }
    }

}