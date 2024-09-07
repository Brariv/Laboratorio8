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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.Layout.MainScreen.MainScreenDestination
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import com.uvg.laboratorio8.Layout.MainScreen.MainScreen
import com.uvg.laboratorio8.Layout.CharactersScreen.navigateToCharactersScreen
import com.uvg.laboratorio8.Layout.CharactersScreen.CharactersDestination
import com.uvg.laboratorio8.Layout.CharactersScreen.CharactersScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.navigateToCharacterDetailsScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailDestination
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailScreen


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
                            navController.navigateToCharactersScreen(
                                destination = CharactersDestination
                            )
                        })
                        CharactersScreen(onCharacterClick = { characterId ->
                            navController.navigateToCharacterDetailsScreen(
                                destination = DetailDestination(
                                    ID = characterId
                                )

                            )
                        })
                        DetailScreen(onNavigateBack = {
                            navController.navigateUp()
                        })





                    }
                }
            }
        }
    }

}

