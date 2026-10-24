package com.alexkravtsov.vkpracticeone.presentation.detailcard

import com.alexkravtsov.vkpracticeone.model.AppModel

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Content(val apps: List<AppModel>) : AppDetailsState
    data object Error : AppDetailsState
}