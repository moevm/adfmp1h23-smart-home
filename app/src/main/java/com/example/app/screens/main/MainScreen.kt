package com.example.app.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_home.R
import com.example.app.screens.main.models.FurnitureItemStates
import com.example.app.screens.main.models.FurnitureModel
import com.example.app.screens.main.models.RoomModel
import com.example.app.ui.theme.*
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToAddItem : () -> Unit,
    roomList: SnapshotStateList<RoomModel>,
    furnitureList: SnapshotStateList<FurnitureModel>,
    currentRoom: MutableState<Int>
) {
    val mainViewModel = viewModel<MainViewModel>()

    var editingButtonIsChecked by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val mainScreenStates = remember(editingButtonIsChecked) {
        if (editingButtonIsChecked) MainScreenStates.DeletingState
        else MainScreenStates.SimpleState
    }

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    var textFieldValue by remember {
        mutableStateOf("")
    }

    var dialogIsActive by remember {
        mutableStateOf(false)
    }

    var dialogNameActive by remember {
        mutableStateOf(false)
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
                                color = if (isPressed) BlueLight else PurpleMedium,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(8.dp),
                        interactionSource = interactionSource,
                        onClick = {
                            dialogIsActive = !dialogIsActive
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
        Box(modifier = Modifier.fillMaxSize()) {
            if (dialogIsActive) {
                Dialog(onDismissRequest = { dialogIsActive = !dialogIsActive }) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.Center),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PurpleDialog
                        ),
                        elevation = CardDefaults.elevatedCardElevation(0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 32.dp, horizontal = 50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(16.dp),
                                    imageVector = Icons.Outlined.Add,
                                    tint = TextPurple,
                                    contentDescription = null
                                )
                                TextButton(onClick = {
                                    dialogIsActive = !dialogIsActive
                                    dialogNameActive = !dialogNameActive
                                }) {
                                    Text(
                                        modifier = Modifier.padding(start = 8.dp),
                                        text = "Добавить комнату",
                                        color = TextPurple
                                    )
                                }

                            }
                            Row(
                                modifier = Modifier.padding(top = 20.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(16.dp),
                                    imageVector = Icons.Outlined.Add,
                                    tint = TextPurple,
                                    contentDescription = null
                                )
                                TextButton(onClick = {
                                    dialogIsActive = !dialogIsActive
                                    currentRoom.value = roomList[selectedIndex].id
                                    navigateToAddItem()
                                }) {
                                    Text(
                                        modifier = Modifier.padding(start = 8.dp),
                                        text = "Добавить устройство",
                                        color = TextPurple
                                    )
                                }

                            }
                        }
                    }
                }

            }

            if (dialogNameActive) {
                Dialog(onDismissRequest = { dialogNameActive = !dialogNameActive }) {
                    Card(
                        modifier = Modifier.align(Alignment.Center),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PurpleDialog
                        ),
                        elevation = CardDefaults.elevatedCardElevation(0.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 4.dp, end = 4.dp),
                                onClick = { dialogNameActive = !dialogNameActive }) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    tint = White,
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(
                                        top = 40.dp,
                                        bottom = 12.dp,
                                        start = 20.dp,
                                        end = 20.dp
                                    )
                                    .align(Alignment.BottomEnd)
                            ) {
                                TextField(
                                    modifier = Modifier.padding(bottom = 24.dp),
                                    value = textFieldValue,
                                    onValueChange = { newValue ->
                                        textFieldValue = newValue
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Название",
                                            fontSize = 16.sp,
                                            color = Grey
                                        )
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        textColor = Grey,
                                        containerColor = Color.Transparent,
                                        cursorColor = Grey,
                                        unfocusedIndicatorColor = Grey,
                                        focusedIndicatorColor = Grey,
                                        placeholderColor = Grey
                                    )
                                )
                                TextButton(
                                    modifier = Modifier.background(
                                        color = Green,
                                        shape = RoundedCornerShape(32.dp)
                                    ),
                                    onClick = {
                                        roomList.add(RoomModel(id = roomList.last().id + 1, text = textFieldValue))
                                        dialogNameActive = !dialogNameActive
                                    }) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 8.dp,
                                                ),

                                            text = "Добавить",
                                            color = White
                                        )
                                    }


                                }

                            }
                        }
                    }

                }
            }
        }

        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            ScrollableTabRow(
                modifier = Modifier.padding(20.dp),
                selectedTabIndex = selectedIndex,
                edgePadding = 0.dp,
                divider = {},
                indicator = {}
            ) {
                roomList.forEachIndexed { index, room ->
                    Tab(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        interactionSource = NoRippleInteractionSource(),
                        selectedContentColor = Color.Unspecified,
                        unselectedContentColor = Color.Unspecified
                    ) {
                        RoomItem(
                            modifier = Modifier,
                            text = room.text,
                            distance = abs(index - selectedIndex)
                        )
                    }
                }
            }

            if (mainScreenStates == MainScreenStates.DeletingState && roomList[selectedIndex].id != 0) {
                Surface(
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),
                    color = Purple282832
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 48.dp,
                                end = 12.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val currentText = roomList.getOrNull(selectedIndex)?.text
                        Text(
                            text = currentText?:"",
                            color = White,
                            fontSize = 16.sp
                        )
                        IconButton(onClick = {
                            while(furnitureList.find { it.room == roomList[selectedIndex].id } != null)
                                furnitureList.remove(furnitureList.find { it.room == roomList[selectedIndex].id })
                            roomList.removeAt(selectedIndex--)
                        }) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Outlined.Delete,
                                tint = White,
                                contentDescription = null
                            )
                        }
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
                items(furnitureList.size) { currentFurniture ->
                    if (roomList[selectedIndex].id == 0 || roomList[selectedIndex].id == furnitureList[currentFurniture].room) {
                        FurnitureItemUI(
                            furnitureItemStates =
                            if (mainScreenStates == MainScreenStates.SimpleState) FurnitureItemStates.SimpleState
                            else FurnitureItemStates.DeletedState,
                            onDelete = {
                                furnitureList.remove(furnitureList[currentFurniture])
                            },
                            furnitureModel = furnitureList[currentFurniture]
                        )
                    }
                }

            }
        }
    }
}
