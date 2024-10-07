package com.example.zencar_test.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.zencar_test.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject


class ValidateRegistrationImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ValidateRegistration {
    override val nameHandler =
        fun(next: ValidateHandler<RegistrationParams, UpdateUserDataResult>) =
            fun(request: RegistrationParams): UpdateUserDataResult =
                when {
                    request.name.isEmpty() -> UpdateUserDataResult.NameError(context.getString(R.string.validate_empty_name))
                    (request.name.count { it.isLetter() } < 3) -> UpdateUserDataResult.NameError(context.getString(R.string.validate_name_contain))
                    else -> next(request)
                }
    override val passwordHandler =
        fun(next: ValidateHandler<RegistrationParams, UpdateUserDataResult>) =
            fun(request: RegistrationParams): UpdateUserDataResult =
                when {
                    request.password.length < 8 -> UpdateUserDataResult.PasswordError(
                        context.getString(R.string.validate_password_contain)
                    )

                    request.password != request.confirmPassword -> UpdateUserDataResult.ConfirmPasswordError(
                        context.getString(R.string.validate_password_not_match)
                    )

                    else -> next(request)
                }
    override val birthdayHandler =
        fun(next: ValidateHandler<RegistrationParams, UpdateUserDataResult>) =
            fun(request: RegistrationParams): UpdateUserDataResult =
                when {
                    !isValidDate(request.birthday) -> UpdateUserDataResult.BirthdayError(
                        context.getString(R.string.validate_invalid_date)
                    )
                    else -> next(request)
                }

    @SuppressLint("NewApi")
    private fun isValidDate(date: String): Boolean {
        return try {
            val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
            val localDate = LocalDate.parse(date, formatter)

            val month = localDate.monthValue
            val year = localDate.year

            month in 1..12 && year <= 2024
        } catch (e: DateTimeParseException) {
            false
        }
    }

}


sealed interface UpdateUserDataResult {
    data class NameError(val message: String) : UpdateUserDataResult
    data class PasswordError(val message: String) : UpdateUserDataResult
    data class ConfirmPasswordError(val message: String) : UpdateUserDataResult
    data class BirthdayError(val message: String) : UpdateUserDataResult
    object Success : UpdateUserDataResult
}

data class RegistrationParams(
    val name: String,
    val password: String,
    val birthday: String,
    val confirmPassword: String,
)

interface ValidateRegistration {
    val nameHandler: (ValidateHandler<RegistrationParams, UpdateUserDataResult>) -> (RegistrationParams) -> UpdateUserDataResult
    val passwordHandler: (ValidateHandler<RegistrationParams, UpdateUserDataResult>) -> (RegistrationParams) -> UpdateUserDataResult
    val birthdayHandler: (ValidateHandler<RegistrationParams, UpdateUserDataResult>) -> (RegistrationParams) -> UpdateUserDataResult
}

typealias ValidateHandler<T, R> = (request: T) -> R
