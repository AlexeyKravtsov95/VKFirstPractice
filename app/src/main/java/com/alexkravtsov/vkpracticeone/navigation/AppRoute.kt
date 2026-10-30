package com.alexkravtsov.vkpracticeone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alexkravtsov.vkpracticeone.data.AppModelMapper
import com.alexkravtsov.vkpracticeone.data.appsData
import com.alexkravtsov.vkpracticeone.data.showcaseApps
import com.alexkravtsov.vkpracticeone.presentation.applist.AppListScreen
import com.alexkravtsov.vkpracticeone.presentation.detailcard.AppDetailsScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val catalog = remember {
        val mapper = AppModelMapper()
        (appsData + showcaseApps)
            .map { mapper.map(it) }
            .distinctBy { it.name }
            .associateBy { it.name }
    }
    NavHost(navController = navController, startDestination = AppListRoute) {
        composable<AppListRoute> {
            AppListScreen(
                onAppClick = { app -> navController.navigate(AppDetailsRoute(app.name)) }
            )
        }
        composable<AppDetailsRoute> { entry ->
            val route = entry.toRoute<AppDetailsRoute>()
            val app = catalog[route.name] ?: return@composable
            AppDetailsScreen(
                app = app,
                onShowcaseAppClick = { showcaseApp ->
                    navController.navigate(AppDetailsRoute(showcaseApp.name)) {
                        launchSingleTop = true
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}