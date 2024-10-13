package com.example.zencar_test.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Error(
        val exception: Throwable? = null,
        val responseBody: ResponseBody? = null,
        val errorMessageRes: String = "Неизвестная ошибка",
    ) : Result<Nothing>

    data object Loading : Result<Nothing>
}


fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }
}

suspend fun <T> Flow<T>.collectAsResult(
    onSuccess: suspend (T) -> Unit = {},
    onError: suspend (ex: Throwable, exStr: String) -> Unit = { _, _ -> },
    onLoading: () -> Unit = {},
) {
    asResult().collect { result ->
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error -> {
                onError(
                    result.exception ?: UnknownError(),
                    "Неизвестная ошибка",
                )
            }
            Result.Loading -> onLoading()
        }
    }
}