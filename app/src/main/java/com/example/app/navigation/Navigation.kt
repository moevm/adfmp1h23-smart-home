package com.example.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app.navigation.ProjectDestinations.MAIN_ROUTE
import com.example.app.navigation.ProjectDestinations.SEARCH_ROUTE
import com.example.app.screens.AddScreen
import com.example.app.screens.main.MainScreen
import com.example.app.screens.main.models.FurnitureModel
import com.example.app.screens.main.models.RoomModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    roomList: SnapshotStateList<RoomModel>,
    furnitureList: SnapshotStateList<FurnitureModel>,
    addFurniture: (name: String) -> Any,
    currentRoom: MutableState<Int>
) {
    val navController = rememberNavController()
    Box(modifier = modifier) {
        NavHost(navController = navController, startDestination = MAIN_ROUTE) {
            composable(MAIN_ROUTE) {
                MainScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    navigateToAddItem = {
                        navController.navigate(SEARCH_ROUTE)
                    },
                    roomList = roomList,
                    furnitureList = furnitureList,
                    currentRoom = currentRoom
                )
            }
            composable(SEARCH_ROUTE) {
                AddScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    navigateBack = {
                        navController.popBackStack()
                    },
                    addFurniture = addFurniture
                )
            }
        }
    }
}

object ProjectDestinations {
    const val MAIN_ROUTE = "main"
    const val SEARCH_ROUTE = "search"
}
