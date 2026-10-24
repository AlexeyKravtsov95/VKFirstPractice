package com.alexkravtsov.vkpracticeone.presentation.applist

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}