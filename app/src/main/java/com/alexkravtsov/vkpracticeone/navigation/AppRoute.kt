package com.alexkravtsov.vkpracticeone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alexkravtsov.vkpracticeone.model.appsData
import com.alexkravtsov.vkpracticeone.model.showcaseApps
import com.alexkravtsov.vkpracticeone.screen.applist.AppListScreen
import com.alexkravtsov.vkpracticeone.screen.detailcard.AppDetailsScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val catalog = remember {
        (appsData + showcaseApps).distinctBy { it.name }.associateBy { it.name }
    }
    NavHost(navController = navController, startDestination = AppListRoute) {
        composable<AppListRoute> {
            AppListScreen(
                apps = appsData,
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