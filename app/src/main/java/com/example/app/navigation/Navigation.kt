package com.example.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.navigation.ProjectDestinations.MAIN_ROUTE
import com.example.app.navigation.ProjectDestinations.SEARCH_ROUTE

@Composable
fun Navigation(
    modifier : Modifier = Modifier
) {
    val navController = rememberNavController()
    Box(modifier =modifier){
        NavHost(navController = navController, startDestination = MAIN_ROUTE){
            composable(MAIN_ROUTE) {

            }
            composable(SEARCH_ROUTE) {

            }
        }
    }
}
object ProjectDestinations {
    const val MAIN_ROUTE = "main"
    const val SEARCH_ROUTE = "search"
}
