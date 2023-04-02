package com.example.app.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_home.R
import com.example.app.screens.RoomItem
import com.example.app.screens.main.models.FurnitureItemStates
import com.example.app.screens.main.models.FurnitureModel
import com.example.app.ui.theme.*
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {

    var editingButtonIsChecked by remember {
        mutableStateOf(false)
    }
    var addingButtonIsSelected by remember {
        mutableStateOf(false)
    }

    val mainScreenStates = remember(editingButtonIsChecked) {
        if (editingButtonIsChecked) MainScreenStates.DeletingState
        else MainScreenStates.SimpleState
    }

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val rooms = remember {
        mutableStateListOf(
            "Все",
            "Столовая",
            "Кухня",
            "Спальня",
            "Ванная"
        )
    }


    val picture = painterResource(id = R.drawable.image_photo_room)

    val furniture = remember(mainScreenStates) {
        mutableStateListOf(
            FurnitureModel(
                isSelected = false,
                text = "Торшер",
                painter = picture
            ),
            FurnitureModel(
                isSelected = false,
                text = "Торшер",
                painter = picture
            ),
            FurnitureModel(
                isSelected = false,
                text = "Торшер",
                painter = picture
            ),
            FurnitureModel(
                isSelected = false,
                text = "Торшер",
                painter = picture
            ),
            FurnitureModel(
                isSelected = false,
                text = "Торшер",
                painter = picture
            )
        )
    }

    Scaffold(
        modifier = modifier.background(ProjectBlack),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Мой дом", style = TextStyle().copy(
                        color = Color.White,
                        fontSize = 22.sp,
                    )
                )
            }
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = PurpleDark
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 42.dp,
                            vertical = 12.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .background(
                                color = if (!addingButtonIsSelected) PurpleMedium else BlueLight,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(8.dp),
                        onClick = {
                            //добавить диалоговое окно
                        }

                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Outlined.Add,
                            tint = PurpleLight,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .background(
                                color = if (!editingButtonIsChecked) PurpleMedium else BlueLight,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(8.dp),
                        onClick = {
                            editingButtonIsChecked = !editingButtonIsChecked
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            tint = PurpleLight,
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    }
                }
            }

        }
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
                    .padding(12.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                columns = GridCells.Fixed(2)
            ) {

                items(furniture.size) { currentFurniture ->
                    FurnitureItemUI(
                        furnitureItemStates =
                            if (mainScreenStates == MainScreenStates.SimpleState) FurnitureItemStates.SimpleState
                            else FurnitureItemStates.DeletedState,
                        onDelete = {
                            furniture.remove(furniture[currentFurniture])
                        },
                        furnitureModel = furniture[currentFurniture]
                    )
                }

            }
        }

    }
}
