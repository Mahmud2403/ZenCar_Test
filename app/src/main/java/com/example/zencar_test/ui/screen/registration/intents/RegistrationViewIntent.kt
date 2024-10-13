package com.example.zencar_test.ui.screen.registration.intents

import android.graphics.Bitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.zencar_test.base.BaseViewIntent
import com.example.zencar_test.ui.screen.login.intents.LoginViewIntent

sealed class RegistrationViewIntent: BaseViewIntent {
    data class OnSaveImage(val uri: String): RegistrationViewIntent()

    data class OnChangeName(val name: String): RegistrationViewIntent()
    data class OnChangePassword(val password: String): RegistrationViewIntent()
    data class OnChangeConfirmPassword(val confirmPassword: String): RegistrationViewIntent()
    data class OnChangeBirthday(val birthday: String): RegistrationViewIntent()
    data class OnDateSelect(val birthday: Long): RegistrationViewIntent()

    data object InsertUser: RegistrationViewIntent()
}