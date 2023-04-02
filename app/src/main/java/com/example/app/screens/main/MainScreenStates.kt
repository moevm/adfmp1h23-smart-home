package com.example.app.screens.main

sealed class MainScreenStates {
    object DeletingState : MainScreenStates()
    object SimpleState : MainScreenStates()
}
