package com.example.app.screens.main.models

sealed class FurnitureItemStates {
    object SimpleState : FurnitureItemStates()
    object DeletedState : FurnitureItemStates()
}
