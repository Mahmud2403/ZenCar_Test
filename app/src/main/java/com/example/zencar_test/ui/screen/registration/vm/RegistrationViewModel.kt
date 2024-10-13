package com.example.zencar_test.ui.screen.registration.vm

import android.util.Log
import androidx.compose.ui.util.trace
import com.example.zencar_test.TAG
import com.example.zencar_test.base.BaseViewModel
import com.example.zencar_test.data.util.collectAsResult
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.RegistrationUserRepository
import com.example.zencar_test.ui.screen.registration.intents.RegistrationViewIntent
import com.example.zencar_test.ui.screen.registration.intents.RegistrationViewState
import com.example.zencar_test.utils.RegistrationParams
import com.example.zencar_test.utils.UpdateUserDataResult
import com.example.zencar_test.utils.ValidateRegistration
import com.example.zencar_test.utils.convertMillisToDate
import com.example.zencar_test.utils.formatLocalDateInDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: RegistrationUserRepository,
    private val validate: ValidateRegistration,
) : BaseViewModel<RegistrationViewState, RegistrationViewIntent>(
    initialState = RegistrationViewState()
) {
    private val resultChanel = Channel<Boolean>()
    val registerResult = resultChanel.receiveAsFlow()
        .flowOn(Dispatchers.Main.immediate)


    override fun observe(event: RegistrationViewIntent) {
        when (event) {
            is RegistrationViewIntent.OnSaveImage -> {
                saveImage(event.uri)
            }

            is RegistrationViewIntent.InsertUser -> {
                validateInsertUser()
            }

            is RegistrationViewIntent.OnChangeBirthday -> {
                changeBirthday(event.birthday)
            }

            is RegistrationViewIntent.OnChangeName -> {
                changeName(event.name)
            }

            is RegistrationViewIntent.OnChangePassword -> {
                changePassword(event.password)
            }

            is RegistrationViewIntent.OnDateSelect -> {
                selectDate(event.birthday)
            }

            is RegistrationViewIntent.OnChangeConfirmPassword -> {
                changeConfirmPassword(event.confirmPassword)
            }
        }
    }


    private fun saveImage(uri: String) {
        updateStateFromIo {
            copy(
                img = uri
            )
        }
    }

    private fun insertUser() {
        launchIOCoroutine {
            val dateCreated = formatLocalDateInDate(LocalDateTime.now())
            repository.insertUser(
                User(
                    img = viewStateData.img,
                    name = viewStateData.name,
                    birthday = viewStateData.birthday,
                    password = viewStateData.password,
                    dateCreated = dateCreated,
                )
            ).collectAsResult(
                onSuccess = { id ->
                    updateState {
                        copy(
                            isLoading = false,
                            error = null,
                            name = "",
                            img = "",
                            birthday = "",
                            password = "",
                            confirmPassword = "",
                        )
                    }
                    resultChanel.send(true)
                },
                onLoading = {
                    updateState {
                        copy(
                            isLoading = true,
                            error = null,
                        )
                    }
                },
                onError = { ex, _ ->
                    updateState {
                        copy(
                            error = ex.message.toString(),
                            isLoading = false,
                        )
                    }
                }
            )
        }
    }


    private fun validateInsertUser() {
        launchCoroutine {
            val params: RegistrationParams
            with(viewStateData.copy()) {
                params = RegistrationParams(
                    name = name,
                    password = password,
                    confirmPassword = confirmPassword,
                    birthday = birthday
                )
            }
            val chain: (RegistrationParams) -> UpdateUserDataResult
            with(validate) {
                chain = nameHandler(
                    birthdayHandler(
                        passwordHandler {
                            UpdateUserDataResult.Success
                        }
                    )
                )
            }
            when (val result = chain(params)) {
                is UpdateUserDataResult.NameError -> {
                    updateState {
                        copy(
                            nameError = result.message,
                        )
                    }
                }

                is UpdateUserDataResult.PasswordError -> {
                    updateState {
                        copy(
                            passwordError = result.message,
                        )
                    }
                }

                is UpdateUserDataResult.ConfirmPasswordError -> {
                    updateState {
                        copy(
                            confirmPasswordError = result.message,
                        )
                    }
                }

                is UpdateUserDataResult.BirthdayError -> {
                    updateState {
                        copy(
                            birthdayError = result.message,
                        )
                    }
                }

                UpdateUserDataResult.Success -> {
                    insertUser()
                }

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

    private fun changeConfirmPassword(confirmPassword: String) {
        updateState {
            copy(
                confirmPassword = confirmPassword,
                confirmPasswordError = null,
            )
        }
    }

    private fun changeBirthday(birthday: String) {
        updateState {
            copy(
                birthday = birthday,
                birthdayError = null,
            )
        }
    }

    private fun selectDate(date: Long) {
        val birthday = convertMillisToDate(date)
        updateState {
            copy(
                birthday = birthday,
                birthdayError = null,
            )
        }
    }
}