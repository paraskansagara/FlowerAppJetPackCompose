package com.paras.flowerapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paras.flowerapp.R
import com.paras.flowerapp.theme.ghost_white
import com.paras.flowerapp.utils.BottomScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    val screens = listOf(
        BottomScreen.Home,
        BottomScreen.Cart,
        BottomScreen.Settings
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = ghost_white,
            bottomBar = {
                BottomBar(
                    navController = navController,
                    screens = screens
                )
            },

        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = BottomScreen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(BottomScreen.Home.route) {
                  HomeScreen()
                }
                composable(BottomScreen.Cart.route) {
//                ProfileScreen()
                }
                composable(BottomScreen.Settings.route) {
//                SettingsScreen()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}