package com.example.app.screens.main.models

import androidx.compose.ui.graphics.painter.Painter

data class FurnitureModel(
    var isSelected: Boolean,
    val text: String,
    val painter: Painter?
)
