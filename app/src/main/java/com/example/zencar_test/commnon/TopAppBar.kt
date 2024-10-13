package com.example.zencar_test.commnon

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zencar_test.R
import com.example.zencar_test.TAG
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.ui.screen.registration.components.UserAvatar
import com.example.zencar_test.utils.formatDateAndTime


@Preview
@Composable
private fun ZenCarTopAppBarPreview() {
    HomeTopAppBar(
        user = User.mock,
        actions = {
            Icon(
                imageVector = Icons.Rounded.LightMode,
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
private fun AuthorizationTopBarPreview() {
    AuthorizationTopBar(title = "Вход")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    user: User,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                UserAvatar(
                    modifier = Modifier
                        .size(48.dp),
                    imageUri = user.img,
                )
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp,
                            horizontal = 12.dp
                        )
                ) {
                    Text(
                        modifier = Modifier,
                        text = user.name,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = context.getString(
                            R.string.date_created,
                            formatDateAndTime(user.dateCreated)
                        ),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        actions = actions,
        navigationIcon = navigationIcon,
        expandedHeight = 76.dp
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