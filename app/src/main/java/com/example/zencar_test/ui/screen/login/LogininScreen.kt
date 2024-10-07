package com.example.zencar_test.ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zencar_test.commnon.AuthorizationTopBar
import com.example.zencar_test.commnon.ErrorMessage
import com.example.zencar_test.commnon.ZenCarButton
import com.example.zencar_test.commnon.ZenCarTextField
import com.example.zencar_test.ui.screen.login.intents.LoginViewIntent
import com.example.zencar_test.ui.screen.login.vm.LoginViewModel
import com.example.zencar_test.ui.theme.ButtonColor
import com.example.zencar_test.ui.theme.SecondaryCyan
import com.example.zencar_test.utils.VerticalSpace
import com.example.zencar_test.utils.clickableWithRipple
import com.example.zencar_test.utils.toggle


@Preview
@Composable
fun LogInScreenPreview() {

}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    var showPassword by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            AuthorizationTopBar(
                title = "Вход в приложение",
            )
        },
        bottomBar = {
            TextButton(
                modifier = Modifier,
                onClick = {},
                content = {
                    Text(
                        modifier = Modifier,
                        text = "Регистрация",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    )
                }
            )
        },
        containerColor = SecondaryCyan,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = !state.nameError.isNullOrEmpty(),
            ) {
                ErrorMessage(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp),
                    text = state.nameError ?: ""
                )
            }
            ZenCarTextField(
                value = state.name,
                onValueChange = { name ->
                    viewModel.perform(LoginViewIntent.OnChangeName(name))
                },
                placeholder = "Введите имя пользователя",
                border = if (state.nameError.isNullOrEmpty()) null else BorderStroke(
                    1.dp,
                    Color.Red
                ),
                shape = MaterialTheme.shapes.medium,
                innerPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 18.dp,
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            VerticalSpace(height = 14.dp)
            AnimatedVisibility(
                visible = !state.passwordError.isNullOrEmpty(),
            ) {
                ErrorMessage(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp),
                    text = state.passwordError ?: ""
                )
            }
            ZenCarTextField(
                value = state.password,
                onValueChange = { password ->
                    viewModel.perform(LoginViewIntent.OnChangePassword(password))
                },
                placeholder = "Введите пароль",
                border = if (state.passwordError.isNullOrEmpty()) null else BorderStroke(
                    1.dp,
                    Color.Red
                ),
                shape = MaterialTheme.shapes.medium,
                innerPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = when (showPassword) {
                    true -> VisualTransformation.None
                    false -> PasswordVisualTransformation()
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickableWithRipple(
                            onClick = {
                                showPassword = showPassword.toggle()
                            },
                        ),
                        imageVector = if (!showPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            )
            VerticalSpace(height = 36.dp)
            ZenCarButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.perform(LoginViewIntent.OnClickLogin)
                },
                isLoading = false,
                backgroundColor = ButtonColor,
            ) {
                Text(
                    modifier = Modifier,
                    text = "ВОЙТИ",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                )
            }
        }
    }
}