package com.alexkravtsov.vkpracticeone.presentation.applist

import com.alexkravtsov.vkpracticeone.domain.AppModel

sealed interface AppListState {
    data object Loading : AppListState
    data class Content(val apps: List<AppModel>) : AppListState
    data object Error : AppListState
}