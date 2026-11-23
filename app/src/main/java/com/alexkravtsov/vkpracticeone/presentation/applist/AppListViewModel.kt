package com.alexkravtsov.vkpracticeone.presentation.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexkravtsov.vkpracticeone.domain.AppModelRepository
import com.alexkravtsov.vkpracticeone.domain.GetAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "RatingsBenchmark"

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase,
    private val repository: AppModelRepository
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

                loadRatings(app.map { it.id })
            } catch (e: Exception) {
                _state.value = AppListState.Error
            }
        }
    }

    private suspend fun loadRatings(ids: List<String>) {
        val sequentialTime = measureTimeMillis {
            setRatings(repository.getRatingsSequentially(ids))
        }
        Log.d(TAG, "Sequential: ${ids.size} apps in $sequentialTime ms")

        val parallelTime = measureTimeMillis {
            setRatings(repository.getRatingsInParallel(ids))
        }
        Log.d(TAG, "Parallel: ${ids.size} apps in $parallelTime ms")
    }

    private fun setRatings(ratings: Map<String, Float>) {
        _state.update { state ->
            if (state is AppListState.Content) state.copy(ratings = ratings)
            else state
        }
    }

    fun onToolbarClick() {
        _events.trySend(AppListEvent.ShowSnackbar("Клик по тулбару"))
    }
}
