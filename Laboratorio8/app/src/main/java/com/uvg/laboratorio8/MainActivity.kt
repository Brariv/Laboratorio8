package com.uvg.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.navigateToBottomBarGraph
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.navigateToBottomBarScreen
import com.uvg.laboratorio8.Layout.MainScreen.MainScreenDestination
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import com.uvg.laboratorio8.Layout.MainScreen.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MainScreenDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ){
                        MainScreen(onLoginClick = {
                            navController.navigateToBottomBarGraph(
                                navOptions = NavOptions.Builder().setPopUpTo<MainScreenDestination>(
                                    inclusive = true
                                ).build()
                            )
                        })
                        navigateToBottomBarScreen(
                            onLogoutClick = {
                                navController.navigate(MainScreenDestination){
                                    popUpTo(0)
                                }
                            },
                        )
                    }
                }
            }
        }
    }

}

