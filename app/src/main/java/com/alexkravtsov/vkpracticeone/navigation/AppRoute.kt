package com.alexkravtsov.vkpracticeone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alexkravtsov.vkpracticeone.domain.AppModel
import com.alexkravtsov.vkpracticeone.presentation.applist.AppListScreen
import com.alexkravtsov.vkpracticeone.presentation.detailcard.AppDetailsScreen
import kotlin.reflect.typeOf

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppListRoute) {
        composable<AppListRoute> {
            AppListScreen(
                onAppClick = { app -> navController.navigate(AppDetailsRoute(app)) }
            )
        }
        composable<AppDetailsRoute>(
            typeMap = mapOf(typeOf<AppModel>() to AppModelNavType)
        ) { entry ->
            AppDetailsScreen(
                app = entry.toRoute<AppDetailsRoute>().app,
                onShowcaseAppClick = { showcaseApp ->
                    navController.navigate(AppDetailsRoute(showcaseApp)) {
                        launchSingleTop = true
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}