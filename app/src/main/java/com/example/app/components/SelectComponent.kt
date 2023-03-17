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


@Composable
fun SelectComponent(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { expanded = true }
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray, RoundedCornerShape(4.dp))
                .clickable { expanded = true }
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(0.5f)
                .clickable { expanded = true }) {
                if (selectedOption != null) {
                    Text(
                        text = selectedOption,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                }
                Icon(
                    imageVector = icon,
                    contentDescription = "Select icon",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        expanded = false
                        onOptionSelected(option)
                    })
                }
            }
        }
    }
}
