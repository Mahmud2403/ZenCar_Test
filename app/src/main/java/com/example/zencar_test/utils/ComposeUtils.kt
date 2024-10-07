package com.example.zencar_test.utils

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalUseFallbackRippleImplementation
import androidx.compose.material.ripple
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun VerticalSpace(height: Dp) = Spacer(modifier = Modifier.height(height))

@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

fun Modifier.clickableWithRipple(
    radius: Dp = 18.dp,
    color: Color = Color.Unspecified,
    enabled: Boolean = true,
    bounded: Boolean = false,
    onClick: () -> Unit,
) = composed {
    then(
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rippleOrFallbackImplementation(
                bounded = bounded,
                radius = radius,
                color = color
            ),
            enabled = enabled,
            onClick = onClick
        )
    )
}

@Suppress("DEPRECATION_ERROR")
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun rippleOrFallbackImplementation(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): Indication {
    return if (LocalUseFallbackRippleImplementation.current) {
        rememberRipple(bounded, radius, color)
    } else {
        ripple(bounded, radius, color)
    }
}

@Composable
fun <T> rememberMutableStateOf(initValue: T) = remember {
    mutableStateOf(initValue)
}
