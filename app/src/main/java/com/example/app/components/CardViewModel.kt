package com.example.ai_home.components

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardViewModel(application: Application) : AndroidViewModel(application) {
    private val _cards = MutableStateFlow(
        mutableListOf(
            CardData(1, 1, "Карточка", "room 1"),
            CardData(2, 1, "Карточка", "room 1"),
            CardData(3, 2, "Карточка", "room 2"),
            CardData(4, 2, "Карточка", "room 2"),
            CardData(5, 2, "Карточка", "room 2"),
            CardData(6, 3, "Карточка", "room 3"),
        )
    )
    val cards: StateFlow<List<CardData>> = _cards

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    init {
        saveCards()
        val json = sharedPreferences.getString("cards", null)
        if (json != null) {
            val cards = Gson().fromJson<List<CardData>>(json, object : TypeToken<List<CardData>>() {}.type)
            _cards.value = cards.toMutableList()
        }
    }

    fun addCard(card: CardData) {
        _cards.value.add(card)
        saveCards()
    }

    fun removeCard(cardId: Int) {
        val arr = mutableListOf<CardData>()
        arr.addAll(_cards.value.filter { card -> card.id != cardId })
        _cards.value = arr
        saveCards()
    }

    private fun saveCards() {
        val json = Gson().toJson(_cards.value)
        sharedPreferences.edit().putString("cards", json).apply()
    }
}