package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkravtsov.vkpracticeone.R
import com.alexkravtsov.vkpracticeone.domain.GetShowcaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getShowcaseUseCase: GetShowcaseUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(
        AppDetailsState.Loading
    )

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    private var installJob: Job? = null
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

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
        } else {
            installJob = viewModelScope.launch { emulateInstall() }
        }
    }

    private suspend fun emulateInstall() {
        try {
            var progress = 0
            while (progress < 100) {
                delay(150.milliseconds)
                progress += 5
                setInstallState(InstallState.Installing(progress))
            }
            setInstallState(InstallState.Installed)
        } finally {
            if (!currentCoroutineContext().isActive) setInstallState(InstallState.NotInstalled)
        }
    }

    private fun setInstallState(installState: InstallState) {
        _state.update { state ->
            if (state is AppDetailsState.Content) state.copy(installState = installState)
            else state
        }
    }
}