package com.example.app.screens.main

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app.ui.theme.ProjectBlack
import com.example.app.ui.theme.White
import kotlin.math.max

@Composable
fun RoomItem(
    modifier: Modifier = Modifier,
    text: String,
    distance: Int,
) {
    Text(
        modifier = Modifier.background(ProjectBlack),
        text = text,
        color = White.copy(alpha = max(1f - 0.25f * distance, 0.4f))
    )
}
