package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkravtsov.vkpracticeone.R
import com.alexkravtsov.vkpracticeone.domain.GetShowcaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getShowcaseUseCase: GetShowcaseUseCase
) : ViewModel() {

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

                val showcase = getShowcaseUseCase()
                _state.value = AppDetailsState.Content(showcase)
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
            }
        }
    }

    fun onButtonClick() {
        _events.trySend(AppDetailsEvent.ShowSnackbar(R.string.under_development))
    }
}