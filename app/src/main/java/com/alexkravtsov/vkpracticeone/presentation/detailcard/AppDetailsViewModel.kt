package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkravtsov.vkpracticeone.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppDetailsViewModel : ViewModel() {
    private val repository = AppDetailsRepository()
    private val _state = MutableStateFlow<AppDetailsState>(
        AppDetailsState.Loading
    )

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()
    val state = _state.asStateFlow()

    init {
        getContent()
    }

    fun getContent() {
        viewModelScope.launch {
            try {
                _state.value = AppDetailsState.Loading
                delay(2.seconds)

                val app = repository.get()
                _state.value = AppDetailsState.Content(app)
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
            }
        }
    }

    fun onButtonClick() {
        _events.trySend(AppDetailsEvent.ShowSnackbar(R.string.under_development))
    }
}