package com.example.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.NoRippleInteractionSource
import com.example.app.ui.theme.ProjectBlack
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    var rooms = remember {
        mutableStateListOf(
            "Все",
            "Столовая",
            "Кухня",
            "Спальня",
            "Ванная"
        )
    }

    Scaffold(
        modifier = modifier.background(ProjectBlack),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            ScrollableTabRow(
                modifier = Modifier
                    .padding(top = 20.dp),
                selectedTabIndex = selectedIndex,
                edgePadding = 0.dp,
                divider = {},
                indicator = {}
            ) {
                rooms.forEachIndexed { index, room ->
                    Tab(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        interactionSource = NoRippleInteractionSource(),
                        selectedContentColor = Color.Unspecified,
                        unselectedContentColor = Color.Unspecified
                    ) {
                        RoomItem(
                            modifier = Modifier,
                            text = room,
                            distance = abs(index - selectedIndex)
                        )
                    }
                }
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(2)
            ) {

            }
        }

    }
}
