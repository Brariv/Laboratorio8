package com.uvg.laboratorio8.Layout.Character.CharactersScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.BottomBarScreenDestination
import kotlinx.serialization.Serializable

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
