package com.example.app.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.screens.main.models.CustomSwitch
import com.example.app.screens.main.models.FurnitureItemStates
import com.example.app.screens.main.models.FurnitureModel
import com.example.app.ui.theme.*

@Composable
fun FurnitureItemUI(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    furnitureItemStates: FurnitureItemStates,
    furnitureModel: FurnitureModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 6.dp)
    ) {

        Card(
            modifier = modifier
                .align(Alignment.Center)
                .padding(12.dp)
                .background(
                    White35,
                    shape = RoundedCornerShape(15.dp)
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        White35
                    ),
            ) {

                if (furnitureItemStates == FurnitureItemStates.SimpleState) {
                    CustomSwitch(
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.TopEnd),
                        checkedTrackColor = White50,
                        uncheckedTrackColor = White50,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 6.dp),
                        painter = furnitureModel.painter ?: ColorPainter(White),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = furnitureModel.text,
                        color = White,
                        fontSize = 14.sp
                    )

                }
            }


        }

        if (furnitureItemStates == FurnitureItemStates.DeletedState) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable {
                        onDelete()
                    },
                shape = CircleShape,
                color = Red,
                border = BorderStroke(2.dp, color = White50)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp),
                    imageVector = Icons.Outlined.Delete,
                    tint = White,
                    contentDescription = null
                )
            }
        }
    }

}
