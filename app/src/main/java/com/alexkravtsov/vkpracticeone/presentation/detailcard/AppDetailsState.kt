package com.alexkravtsov.vkpracticeone.presentation.detailcard

import com.alexkravtsov.vkpracticeone.domain.AppModel

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Content(val showcase: List<AppModel>) : AppDetailsState
    data object Error : AppDetailsState
}