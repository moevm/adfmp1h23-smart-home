package com.example.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import com.example.ai_home.R
import com.example.app.navigation.Navigation
import com.example.app.screens.main.models.FurnitureModel
import com.example.app.screens.main.models.RoomModel
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.ProjectBlack

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val rooms = remember {
                mutableStateListOf(
                    RoomModel(id = 0, text = "Все"),
                    RoomModel(id = 1, text = "Столовая"),
                    RoomModel(id = 2, text = "Кухня"),
                    RoomModel(id = 3, text = "Спальня"),
                    RoomModel(id = 4, text = "Ванная")
                )
            }

            val picture = painterResource(id = R.drawable.image_photo_room)

            val furniture = remember {
                mutableStateListOf(
                    FurnitureModel(isSelected = false, text = "Торшер", room = 1, roomName = rooms[1].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Розетка", room = 1, roomName = rooms[1].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Розетка", room = 2, roomName = rooms[2].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Чайник", room = 2, roomName = rooms[2].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Торшер", room = 3, roomName = rooms[3].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Чайник", room = 3, roomName = rooms[3].text, painter = picture),
                    FurnitureModel(isSelected = false, text = "Торшер", room = 4, roomName = rooms[4].text, painter = picture)
                )
            }

            val currentRoom = remember {
                mutableStateOf(0)
            }

            val addFurniture = { name: String ->
                furniture.add(
                    FurnitureModel(
                        isSelected = false,
                        text = name,
                        room = currentRoom.value,
                        roomName = rooms.find { it.id == currentRoom.value }!!.text,
                        painter = picture
                    )
                )
            }

            WindowCompat.setDecorFitsSystemWindows(window, false)
            AppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Navigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                        roomList = rooms,
                        furnitureList = furniture,
                        addFurniture = addFurniture,
                        currentRoom = currentRoom
                    )
                }
            }
        }
    }
}
