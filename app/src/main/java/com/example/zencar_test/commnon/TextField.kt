package com.example.zencar_test.commnon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(
    showBackground = true
)
@Composable
private fun ZenCarTextFieldPreview() {
    ZenCarTextField(
        value = "",
        onValueChange = {},
        placeholder = "example@example.ru"
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ZenCarTextFieldPassword() {
    ZenCarTextField(
        value = "",
        onValueChange = {},
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.RemoveRedEye,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        placeholder = "password",
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ZenCarTextField(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    innerPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 21.dp),
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    value: String,
    border: BorderStroke? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shadowElevation: Float = 0f,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String = "",
    enable: Boolean = true,
    isError: Boolean = false,
    backgroundColor: Color = Color.White,
    placeholderTextColor: Color = Color.Gray,
    cursorBrush: Brush = SolidColor(Color.Black),
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    Row(
        modifier = modifier
            .graphicsLayer(
                shape = shape,
                clip = true,
                shadowElevation = shadowElevation,
                spotShadowColor = Color.Gray
            )
            .then(
                if (border != null) Modifier.border(border, shape = shape)
                else Modifier
            )
            .background(backgroundColor)
            .padding(innerPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.invoke()
        BasicTextField(
            value = value,
            onValueChange = onValueChange::invoke,
            modifier = Modifier.weight(1f),
            textStyle = style,
            interactionSource = interactionSource,
            enabled = enable,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            cursorBrush = cursorBrush,
        ) { innerTextField ->

            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = style,
                        color = placeholderTextColor
                    )
                },
                interactionSource = interactionSource,
                contentPadding = PaddingValues(0.dp),
                visualTransformation = visualTransformation,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = backgroundColor,
                ),
                isError = isError,
            )
        }
        trailingIcon?.invoke()
    }
}