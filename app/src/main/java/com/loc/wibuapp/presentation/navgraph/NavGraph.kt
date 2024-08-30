package com.loc.wibuapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.wibuapp.presentation.onboarding.OnBoardingScreen
import com.loc.wibuapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.WibuNavigation.route,
            startDestination = Route.WibuNavigatorScreen.route,
        ) {
            composable(route = Route.WibuNavigatorScreen.route) {
                Text(text = "Wibu Navigator Screen")
            }
        }
    }
}