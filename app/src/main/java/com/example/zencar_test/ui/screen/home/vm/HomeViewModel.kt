package com.example.zencar_test.ui.screen.home.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.zencar_test.TAG
import com.example.zencar_test.base.BaseViewModel
import com.example.zencar_test.data.local_source.datastore.ZenCarStorage
import com.example.zencar_test.data.util.collectAsResult
import com.example.zencar_test.domain.repository.HomeRepository
import com.example.zencar_test.ui.screen.home.intents.HomeViewIntent
import com.example.zencar_test.ui.screen.home.intents.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val storage: ZenCarStorage,
) : BaseViewModel<HomeViewState, HomeViewIntent>(
    initialState = HomeViewState(isLoading = true)
) {

    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    init {
        getUsers()
        getUserByName()
    }

    override fun observe(event: HomeViewIntent) {
        when (event) {
            is HomeViewIntent.OnDeleteUser -> {
                deleteUser(viewStateData.user.id, event.id)
            }

            HomeViewIntent.OnLogout -> {
                logout()
            }

            HomeViewIntent.ToggleTheme -> {
                toggleTheme()
            }
        }
    }

    private fun getUsers() {
        launchIOCoroutine {
            repository.getAllUsers().collectAsResult(
                onSuccess = { users ->
                    updateStateFromIo {
                        copy(
                            listUser = users,
                            isLoading = false,
                            error = null
                        )
                    }
                    users.forEach {
                        Log.e(TAG, "getUsers: ${it.img}")
                    }
                },
                onError = { ex, _ ->
                    updateState {
                        copy(
                            isLoading = false,
                            error = ex.message.toString()
                        )
                    }
                },
                onLoading = {
                    updateState {
                        copy(
                            isLoading = true,
                        )
                    }
                }
            )
        }
    }

    private fun getUserByName() {
        launchIOCoroutine {
            storage.userName.collect { name ->
                if (!name.isNullOrEmpty()) {
                    repository.getUserByName(name).collectAsResult(
                        onSuccess = { user ->
                            updateState {
                                copy(
                                    user = user,
                                )
                            }
                            Log.e(TAG, "getUserByName: ${user.img}")
                        },
                        onError = { ex, _ ->
                        },
                        onLoading = {
                        }
                    )
                }
            }
        }
    }

    private fun logout() {
        launchCoroutine {
            storage.setLoggedIn(false)
            storage.clearUserName()
            repository.updateUser(user = viewStateData.user.copy(isCurrent = false))
        }
    }

    private fun deleteUser(currentUserId: Int, userId: Int) {
        launchIOCoroutine {
            val currentUser = repository.getUserById(currentUserId).firstOrNull()
            val userToDelete = repository.getUserById(userId).firstOrNull()


            if (currentUser != null && userToDelete != null) {
                if (userToDelete.dateCreated > currentUser.dateCreated) {
                    repository.deleteUser(userId)
                    val updatedUsers = viewStateData.listUser.filter { it.id != userId }
                    updateState {
                        copy(
                            listUser = updatedUsers
                        )
                    }
                }
            }
        }
    }

    private fun toggleTheme() {
        Log.e(TAG, "HomeScreen: ${_isDarkTheme.value}", )

        _isDarkTheme.value = !_isDarkTheme.value
    }
}