package com.chun.moviecompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Colors.topAppBarContentColor
    get() = if (isLight) Color.White else LightGray

val Colors.statusBarColor
    get() = if (isLight) PurpleGrey40 else Purple80