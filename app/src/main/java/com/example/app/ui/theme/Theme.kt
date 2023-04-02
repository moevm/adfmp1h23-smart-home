package com.example.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent
        )
    }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = ProjectBlack,
            onPrimary = ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
            ProjectBlack,
        )
    ) {
        CompositionLocalProvider(
            content = content
        )
    }
}
