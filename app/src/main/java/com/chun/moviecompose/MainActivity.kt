package com.chun.moviecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.chun.moviecompose.navigation.Screen
import com.chun.moviecompose.navigation.SetUpNavGraph
import com.chun.moviecompose.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MovieComposeTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        SetUpNavGraph(
                            navController = navController,
                            startDestination = Screen.Movie.route
                        )
                    }
                }
            }
        }
    }
}