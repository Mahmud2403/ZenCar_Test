package com.example.zencar_test.commnon

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zencar_test.ui.theme.PrimaryCyan


@Preview
@Composable
fun ZenCarButtonIsLoadingFalse() {
    ZenCarButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        onClick = {},
        backgroundColor = PrimaryCyan,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Save",
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun ZenCarButtonIsLoadingTrue() {
    ZenCarButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        onClick = {},
        backgroundColor = PrimaryCyan,
        isLoading = true,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Save",
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun ZenCarButtonPreview3() {
    ZenCarButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        onClick = {},
        backgroundColor = PrimaryCyan,
        enable = false,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Save",
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}


@Composable
fun ZenCarButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enable: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = PrimaryCyan,
    shadowElevation: Float = 0f,
    onClick: () -> Unit,
    innerPaddingValues: PaddingValues = PaddingValues(10.dp),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .graphicsLayer(
                shape = shape,
                clip = true,
                shadowElevation = shadowElevation,
                spotShadowColor = Color.Gray
            )
            .background(
                animateColorAsState(
                    targetValue = if (isLoading) Color(0xFFB1B9BB) else backgroundColor,
                    animationSpec = tween(300),
                    label = "",
                ).value,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enable && !isLoading,
                onClick = onClick
            )
            .padding(innerPaddingValues),
        contentAlignment = Alignment.Center,
        content = {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (isLoading) 0f else 1f
                    },
                contentAlignment = Alignment.Center,
            ) {
                content(this)
            }
            AnimatedContent(
                targetState = isLoading,
                label = "",
            ) {
                if (it) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                }
            }
        }
    )
}