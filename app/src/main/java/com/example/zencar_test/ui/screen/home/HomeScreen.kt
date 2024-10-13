package com.example.zencar_test.ui.screen.home

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zencar_test.commnon.HomeTopAppBar
import com.example.zencar_test.ui.screen.home.component.UserCard
import com.example.zencar_test.ui.screen.home.intents.HomeViewIntent
import com.example.zencar_test.ui.screen.home.vm.HomeViewModel
import com.example.zencar_test.ui.theme.PrimaryCyan
import com.example.zencar_test.utils.clickableWithRipple
import com.example.zencar_test.utils.formatDateAndTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onClickLogout: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val isDarkTheme by viewModel.isDarkTheme

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                if (!state.isLoading)
                    HomeTopAppBar(
                        user = state.user,
                        actions = {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .fillMaxHeight()
                                    .align(Alignment.CenterVertically)
                                    .clickableWithRipple(
                                        onClick = {
                                            viewModel.perform(HomeViewIntent.ToggleTheme)
                                        }
                                    ),
                                imageVector = Icons.Rounded.LightMode,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = null,
                            )
                        }
                    )
            },
            floatingActionButton = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(180f)
                            .padding(16.dp)
                            .clickableWithRipple(
                                onClick = {
                                    onClickLogout()
                                    viewModel.perform(HomeViewIntent.OnLogout)
                                }
                            ),
                        imageVector = Icons.AutoMirrored.Rounded.Logout,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Start,
            containerColor = MaterialTheme.colorScheme.background,
        ) { innerPadding ->
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding() + 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp
                    )
                    .fillMaxSize()
                    .systemBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = state.listUser,
                    key = { user ->
                        user.id
                    }
                ) { user ->
                    val isDelete = user.dateCreated > state.user.dateCreated
                    val dismissState = rememberDismissState(
                        confirmStateChange = { state ->
                            if (state == DismissValue.DismissedToStart) {
                                viewModel.perform(HomeViewIntent.OnDeleteUser(user.id))
                                true
                            } else {
                                false
                            }
                        }
                    )

                    DisposableEffect(dismissState.currentValue) {
                        onDispose { }
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(
                            DismissDirection.EndToStart,
                        ),

                        background = {
                            val backgroundColor by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> Color.Red.copy(
                                        alpha = 0.8f
                                    )

                                    else -> MaterialTheme.colorScheme.background
                                },
                                label = ""
                            )

                            val iconScale by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.5f,
                                label = ""
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = backgroundColor)
                                    .padding(end = 16.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    modifier = Modifier.scale(iconScale),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.onError,
                                )
                            }
                        },
                        dismissContent = {
                            UserCard(
                                user = user,
                                isDelete = isDelete,
                                onClickDelete = {
                                    viewModel.perform(HomeViewIntent.OnDeleteUser(user.id))
                                },
                            )
                        }
                    )
                }
            }
        }
    }
}
