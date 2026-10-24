package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.annotation.StringRes

sealed interface AppDetailsEvent {
    data class ShowSnackbar(@StringRes val messageRes: Int) : AppDetailsEvent
}