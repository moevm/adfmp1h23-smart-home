package com.example.ai_home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CardData(val id: Int, val title: String, val description: String)

@Composable
fun CardListComponent(room: Int) {

    val cards = listOf(
        CardData(1, "Карточка 2", "room 1"),
        CardData(1, "Карточка 1", "room 1"),
        CardData(1, "Карточка 3", "room 1"),
        CardData(1, "Карточка 4", "room 1"),
        CardData(2, "Карточка 4", "room 2"),
        CardData(2, "Карточка 4", "room 2"),
        CardData(2, "Карточка 4", "room 2"),
        CardData(3, "Карточка 4", "room 3"),
        CardData(3, "Карточка 4", "room 3"),
        CardData(4, "Карточка 4", "room 4"),
        CardData(4, "Карточка 4", "room 4"),
        CardData(4, "Карточка 4", "room 4"),
        CardData(4, "Карточка 4", "room 4")
    )

    val filteredCards = if (room == 0) cards else cards.filter { card -> card.id == room }

    if (filteredCards.size > 0) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.padding(8.dp, bottom = 100.dp),
        ) {
            items(filteredCards) { card ->
                DeviceCardComponent(card)
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            ) {
                Text(text = "Empty..", fontSize = 24.sp)
            }
        }

    }
}
