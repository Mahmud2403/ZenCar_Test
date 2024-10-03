package com.example.zencar_test.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class BaseViewModel<S : BaseUiState, E : BaseUiIntent>(
    initialState: S,
    val dispatcherProvider: CoroutinesDispatcherProvider = CoroutinesDispatcherProvider(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> by lazy { _uiState }

    protected val intent =
        MutableSharedFlow<E>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        intent.onEach(::observe).launchIn(viewModelScope)
    }

    protected fun updateState(block: S.() -> S) {
        _uiState.value = block.invoke(uiState.value)
    }

    protected fun updateStateFromIo(block: S.() -> S) {
        viewModelScope.launch(context = dispatcherProvider.main) {
            _uiState.value = block.invoke(uiState.value)
        }
    }

    protected val uiStateData
        get() = uiState.value

    protected abstract fun observe(event: E)

    fun perform(uiEvent: E) = intent.tryEmit(uiEvent)
    fun uiState(): StateFlow<S> = uiState

    protected fun launchCoroutine(
        isCancelDelay: Boolean = false,
        body: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(
            block = {

                if (!isCancelDelay) {
                    delay(400)
                }
                body()
            }
        )
    }

    protected fun launchIOCoroutine(
        body: suspend CoroutineScope.() -> Unit,
    ): Job {
        return launchCoroutine() {
            withContext(dispatcherProvider.io) {
                body()
            }
        }
    }
}