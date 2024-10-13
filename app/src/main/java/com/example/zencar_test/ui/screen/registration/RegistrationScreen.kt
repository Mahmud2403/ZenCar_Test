package com.example.zencar_test.ui.screen.registration

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zencar_test.R
import com.example.zencar_test.TAG
import com.example.zencar_test.commnon.AuthorizationTopBar
import com.example.zencar_test.commnon.DatePickerModal
import com.example.zencar_test.commnon.ErrorMessage
import com.example.zencar_test.commnon.ZenCarButton
import com.example.zencar_test.commnon.ZenCarTextField
import com.example.zencar_test.ui.screen.registration.components.UserAvatar
import com.example.zencar_test.ui.screen.registration.intents.RegistrationViewIntent
import com.example.zencar_test.ui.screen.registration.vm.RegistrationViewModel
import com.example.zencar_test.ui.theme.ButtonColor
import com.example.zencar_test.ui.theme.SecondaryCyan
import com.example.zencar_test.utils.DateTransformation
import com.example.zencar_test.utils.VerticalSpace
import com.example.zencar_test.utils.clickableWithRipple
import com.example.zencar_test.utils.rememberMutableStateOf
import com.example.zencar_test.utils.toggle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val contentResolver = LocalContext.current.contentResolver

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) {
        it?.let { uri ->
            imageUri = uri
            contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )

            viewModel.perform(RegistrationViewIntent.OnSaveImage(uri.toString()))
        }
    }

    Log.e(TAG, "imageUri: $imageUri", )

    var showPassword by remember {
        mutableStateOf(false)
    }
    var showConfirmPassword by remember {
        mutableStateOf(false)
    }
    var datePickerIsVisible by rememberMutableStateOf(false)

    DatePickerModal(
        visible = datePickerIsVisible,
        onDismissRequest = {
            datePickerIsVisible = false
        },
        onConfirmClick = { date ->
            datePickerIsVisible = false
            viewModel.perform(
                RegistrationViewIntent.OnDateSelect(
                    birthday = date,
                )
            )
        },
        onDismiss = {
            datePickerIsVisible = false
        },
    )
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            AuthorizationTopBar(
                modifier = Modifier
                    .padding(start = 16.dp),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickableWithRipple(
                                onClick = onClickBack
                            ),
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                    )
                },
                title = stringResource(R.string.create_user)
            )
        },
        containerColor = SecondaryCyan,
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp,
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                UserAvatar(
                    imageUri = imageUri,
                    onClickPickImage = {
                        launcher.launch(arrayOf("image/*"))
                    }
                )

                VerticalSpace(height = 24.dp)
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
                        viewModel.perform(RegistrationViewIntent.OnChangeName(name))
                    },
                    border = if (state.nameError.isNullOrEmpty()) null else BorderStroke(
                        1.dp,
                        Color.Red
                    ),
                    placeholder = stringResource(R.string.enter_name_user),
                    shape = MaterialTheme.shapes.medium,
                    innerPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 18.dp,
                    ),
                    isError = !state.nameError.isNullOrEmpty(),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )

                VerticalSpace(height = 14.dp)
                AnimatedVisibility(
                    visible = !state.birthdayError.isNullOrEmpty(),
                ) {
                    ErrorMessage(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),
                        text = state.birthdayError ?: ""
                    )
                }
                ZenCarTextField(
                    value = state.birthday,
                    onValueChange = { birthday ->
                        viewModel.perform(RegistrationViewIntent.OnChangeBirthday(birthday))
                    },
                    shape = MaterialTheme.shapes.medium,
                    isError = !state.birthdayError.isNullOrEmpty(),
                    border = if (state.birthdayError.isNullOrEmpty()) null else BorderStroke(
                        1.dp,
                        Color.Red
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = DateTransformation(),
                    innerPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 16.dp,
                    ),
                    placeholder = stringResource(R.string.specify_date_birth),
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickableWithRipple(
                                    onClick = {
                                        datePickerIsVisible = true
                                    }
                                )
                                .padding(PaddingValues(0.dp)),
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null
                        )
                    }
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
                        viewModel.perform(RegistrationViewIntent.OnChangePassword(password))
                    },
                    placeholder = stringResource(R.string.enter_password),
                    border = if (state.passwordError.isNullOrEmpty()) null else BorderStroke(
                        1.dp,
                        Color.Red
                    ),
                    isError = !state.passwordError.isNullOrEmpty(),
                    shape = MaterialTheme.shapes.medium,
                    innerPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
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

                VerticalSpace(height = 14.dp)
                AnimatedVisibility(
                    visible = !state.confirmPasswordError.isNullOrEmpty(),
                ) {
                    ErrorMessage(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 8.dp),
                        text = state.confirmPasswordError ?: ""
                    )
                }
                ZenCarTextField(
                    value = state.confirmPassword,
                    onValueChange = { confirmPassword ->
                        viewModel.perform(
                            RegistrationViewIntent.OnChangeConfirmPassword(
                                confirmPassword
                            )
                        )
                    },
                    placeholder = stringResource(R.string.repeat_passsword),
                    isError = !state.confirmPasswordError.isNullOrEmpty(),
                    border = if (state.confirmPasswordError.isNullOrEmpty()) null else BorderStroke(
                        1.dp,
                        Color.Red
                    ),
                    shape = MaterialTheme.shapes.medium,
                    innerPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                    ),
                    visualTransformation = when (showConfirmPassword) {
                        true -> VisualTransformation.None
                        false -> PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickableWithRipple(
                                onClick = {
                                    showConfirmPassword = showConfirmPassword.toggle()
                                },
                            ),
                            imageVector = if (!showConfirmPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
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
                        viewModel.perform(RegistrationViewIntent.InsertUser)
                    },
                    isLoading = state.isLoading,
                    enable = !state.isLoading,
                    backgroundColor = ButtonColor,
                ) {
                    Text(
                        modifier = Modifier,
                        text = state.error ?: stringResource(R.string.register_title),
                        style = typography.labelLarge,
                        color = Color.White,
                    )
                }
            }
        }
    }
}