package com.example.zencar_test.commnon

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Preview
@Composable
private fun ZenCarTopAppBarPreview() {
    ZenCarTopAppBar(
        title = "Вход",
    )
}

@Preview
@Composable
private fun AuthorizationTopBarPreview() {
    AuthorizationTopBar(title = "Вход")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZenCarTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(700),
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        actions = actions,
        navigationIcon = navigationIcon,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorizationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        colors = topAppBarColors(
            containerColor = Color(0xFF237A94),
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                modifier = Modifier,
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
            )
        },
        navigationIcon = navigationIcon,
    )
}