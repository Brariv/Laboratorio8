package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uvg.laboratorio8.Layout.BottomBarScreen.BottomBarScreenDestination
import kotlinx.serialization.Serializable
import com.uvg.laboratorio8.Layout.MainScreen.MainScreenDestination

@Serializable
data object CharacterDestination



fun NavController.navigateToCharactersScreen(
    destination: CharacterDestination
) {
    this.navigate(destination) {
        popUpTo(BottomBarScreenDestination) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.CharactersScreen(
    onCharacterClick: (Int) -> Unit
) {
    composable<CharacterDestination> { backStackEntry ->
        val destination: CharacterDestination = backStackEntry.toRoute()
        CharacterRoute(
            onCharacterClick = onCharacterClick
        )
    }
}
