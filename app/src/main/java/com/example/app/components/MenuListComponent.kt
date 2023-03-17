package com.example.ai_home.components

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_home.components.SelectComponent
import com.example.ai_home.components.Accordion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListComponent(title: String, menuItems: List<String>, onItemClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = Shapes.None, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 24.sp)
        }
    }
    SelectComponent(
        "Фильтрация",
        options = listOf("Lamp"),
        selectedOption = "all",
        onOptionSelected = {})
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(menuItems) { menuItem ->
            Accordion(title = menuItem, content = {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "wwww")
                    Text(text = "wwww")
                    Text(text = "wwww")
                    Text(text = "wwww")
                }
            })
        }
    }
}
