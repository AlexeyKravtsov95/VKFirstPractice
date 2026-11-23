package com.alexkravtsov.vkpracticeone.presentation.detailcard

import com.alexkravtsov.vkpracticeone.domain.AppModel

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Content(
        val showcase: List<AppModel>,
        val installState: InstallState = InstallState.NotInstalled
    ) : AppDetailsState
    data object Error : AppDetailsState
}

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}