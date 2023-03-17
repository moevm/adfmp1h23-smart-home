package com.example.ai_home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.room.Room
import com.example.ai_home.components.*

data class RoomData(val id: Int, val title: String)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rooms = mutableListOf<RoomData>(
            RoomData(0, "all")
        )

        fun addNewRoom(id: Int, title: String) {
            rooms.add(RoomData(id, title))
        }

        setContent {
            var room by remember { mutableStateOf(0) }
            var openDialog = remember { mutableStateOf(false) }
            var addRoom = remember { mutableStateOf(false) }
            var roomCount by remember { mutableStateOf(1) };



            fun onAddDeviceClicked() {
                val intent = Intent(this, DeviceActivity::class.java)
                startActivity(intent)
            }


            Scaffold(bottomBar = {
                BottomAppBarComponent(onAddClicked = { openDialog.value = true })
            }) {
                Column {
                    RoomListComponent(onRoomChange = { id -> room = id }, rooms)
                    CardListComponent(room)
                }

                if (openDialog.value) {
                    AddComponent(onAddDeviceClick = {onAddDeviceClicked()},
                        onAddRoomClick = {
                            addRoom.value = true
                            openDialog.value = false
                        }
                    )
                }

                if (addRoom.value) {
                    AddRoomComponent(
                        onCancelClick = { addRoom.value = false },
                        onSaveClick = {
                                title -> addNewRoom(roomCount++, title)
                            addRoom.value = false
                        }
                    )
                }
            }
        }
    }
}

