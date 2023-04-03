package com.example.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_home.R
import com.example.app.screens.addscreen.CategoryItem
import com.example.app.screens.addscreen.models.DeviceCategory
import com.example.app.ui.theme.DropDownMenu
import com.example.app.ui.theme.Grey
import com.example.app.ui.theme.ProjectBlack
import com.example.app.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    addFurniture: (name: String) -> Any
) {

    var expandedState by remember {
        mutableStateOf(false)
    }

    val icon = if (expandedState)
        Icons.Filled.ArrowRight
    else
        Icons.Filled.ArrowDropDown

    val pickerList = listOf(
        "Все",
        "Лампы",
        "Розетки",
        "Чайники",
        "Пылесосы"
    )

    var selectedItem by remember {
        mutableStateOf(pickerList[0])
    }

    val listOfAllCategoryAndDevices = listOf(
        DeviceCategory(
            "Лампы",
            deviceList = listOf(
                "накаливания",
                "светодиодная"
            ),
        ),
        DeviceCategory(
            "Розетки",
            deviceList = listOf(
                "Xiaomi розетка",
                "Умная розетка Hiper IoT P07",
                "Роетка номер 3"
            ),
        ),
        DeviceCategory(
            "Чайники",
            deviceList = listOf(
                "Xiaomi mijia 4500",
                "sony",
                "dexp"
            ),
        ),
        DeviceCategory(
            "Пылесосы",
            deviceList = listOf(
                "Xiaomi 300",
                "philips"
            ),
        ),
    )

    val devicesList = remember(selectedItem) {
        mutableStateListOf(
            listOfAllCategoryAndDevices.filter {
                if (selectedItem.lowercase() == "все") {
                    true
                } else selectedItem.lowercase() == it.title.lowercase()
            }
        ).flatten()
    }

    Scaffold(
        modifier = modifier.background(ProjectBlack),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = navigateBack
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.ArrowBack,
                        tint = White,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Поиск устройства", style = TextStyle().copy(
                        color = Color.White,
                        fontSize = 22.sp,
                    )
                )
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 26.dp,
                    end = 26.dp,
                    bottom = 12.dp
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .size(180.dp),
                painter = painterResource(id = R.drawable.image_find_device),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Список устройств",
                fontSize = 22.sp,
                color = White
            )

            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = "Фильтрация",
                    color = White,
                    fontSize = 20.sp,
                )

                ExposedDropdownMenuBox(
                    expanded = expandedState,
                    onExpandedChange = { expandedState = !expandedState }
                ) {
                    OutlinedTextField(
                        value = selectedItem,
                        onValueChange = {

                        },
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                imageVector = icon,
                                contentDescription = null
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                            textColor = Grey,
                            containerColor = Color.Transparent,
                            focusedBorderColor = Grey,
                            unfocusedBorderColor = Grey,
                            placeholderColor = Grey
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .weight(1f)
                    )
                    ExposedDropdownMenu(
                        expanded = expandedState,
                        onDismissRequest = { expandedState = false }
                    ) {
                        pickerList.forEach { it ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedItem = it
                                    expandedState = false
                                },
                                text = {
                                    Text(
                                        text = it,
                                        fontSize = 14.sp,
                                        color = DropDownMenu
                                    )
                                }
                            )
                        }
                    }

                }
            }
            Column(modifier = Modifier.padding(top = 8.dp)) {
                devicesList.forEach {
                    CategoryItem(
                        deviceCategory = it,
                        addFurniture = addFurniture,
                        navigateBack = navigateBack
                    )
                }
            }

        }

    }
}
