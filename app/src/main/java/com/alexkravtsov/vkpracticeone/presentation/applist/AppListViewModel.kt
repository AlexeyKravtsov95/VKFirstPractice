package com.alexkravtsov.vkpracticeone.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkravtsov.vkpracticeone.domain.GetAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase
) : ViewModel() {

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

                val app = getAppsUseCase()
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