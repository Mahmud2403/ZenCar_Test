package com.example.zencar_test.ui.screen.login.vm

import android.content.Context
import android.util.Log
import com.example.zencar_test.R
import com.example.zencar_test.TAG
import com.example.zencar_test.base.BaseViewModel
import com.example.zencar_test.data.local_source.datastore.ZenCarStorage
import com.example.zencar_test.data.util.collectAsResult
import com.example.zencar_test.domain.repository.LoginRepository
import com.example.zencar_test.ui.screen.login.intents.LoginViewIntent
import com.example.zencar_test.ui.screen.login.intents.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: LoginRepository,
    private val storage: ZenCarStorage,
) : BaseViewModel<LoginViewState, LoginViewIntent>(
    initialState = LoginViewState()
) {

    private val resultChanel = Channel<Boolean>()
    val loginResult = resultChanel.receiveAsFlow()
        .flowOn(Dispatchers.Main.immediate)

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
            repository.getUserByName(viewStateData.name).collectAsResult(
                onSuccess = { user ->
                    when {
                        user.name.isEmpty() -> {
                            updateState {
                                copy(
                                    nameError = context.getString(R.string.validate_empty_name)
                                )
                            }
                        }

                        user.password == viewStateData.password -> {
                            val updatedUser = user.copy(isCurrent = true)
                            Log.e(TAG, "updatedUser: $updatedUser", )
                            repository.updateUser(updatedUser)
                            storage.setLoggedIn(true)
                            storage.setUserName(user.name)
                            resultChanel.send(true)
                        }

                        else -> {
                            updateState {
                                copy(
                                    isLoading = false,
                                    passwordError = context.getString(R.string.validate_password_not_match)
                                )
                            }
                        }
                    }

                },
                onLoading = {
                    updateState {
                        copy(
                            isLoading = true,
                        )
                    }
                },
                onError = { _, _ ->
                    updateState {
                        copy(
                            nameError = context.getString(R.string.validate_name_not_match)
                        )
                    }
                }
            )
        }
    }
}