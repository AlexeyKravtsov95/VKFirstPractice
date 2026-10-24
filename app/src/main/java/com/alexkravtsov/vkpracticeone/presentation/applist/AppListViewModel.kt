package com.alexkravtsov.vkpracticeone.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppListViewModel : ViewModel() {
    private val repository = AppListRepository()

    private val _state = MutableStateFlow<AppListState>(
        AppListState.Loading
    )

    private val _events = Channel<AppListEvent>(BUFFERED)
    val events = _events.receiveAsFlow()
    val state = _state.asStateFlow()

    init {
        getContent()
    }

    fun getContent() {
        viewModelScope.launch {
            try {
                _state.value = AppListState.Loading
                delay(2.seconds)

                val app = repository.get()
                _state.value = AppListState.Content(app)
            } catch (e: Exception) {
                _state.value = AppListState.Error
            }
        }
    }

    fun onToolbarClick() {
        _events.trySend(AppListEvent.ShowSnackbar("Клик по тулбару"))
    }
}