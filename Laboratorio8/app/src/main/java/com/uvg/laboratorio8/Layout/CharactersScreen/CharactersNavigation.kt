package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.uvg.laboratorio8.Layout.MainScreen.MainScreenDestination

@Serializable
data object CharactersDestination

fun NavController.navigateToCharactersScreen(
    destination: CharactersDestination
) {
    this.navigate(destination) {
        popUpTo(MainScreenDestination) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.CharactersScreen(
    onCharacterClick: (Int) -> Unit
) {
    composable<CharactersDestination> { backStackEntry ->
        val destination: CharactersDestination = backStackEntry.toRoute()
        CharacterRoute(
            onCharacterClick = onCharacterClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
