package com.uvg.laboratorio8.Layout.Character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.uvg.laboratorio8.Layout.Character.CharactersScreen.CharacterDestination
import com.uvg.laboratorio8.Layout.Character.CharactersScreen.CharactersScreen
import com.uvg.laboratorio8.Layout.Character.DetailsScreen.DetailDestination
import com.uvg.laboratorio8.Layout.Character.DetailsScreen.DetailScreen
import com.uvg.laboratorio8.Layout.Character.DetailsScreen.navigateToCharacterDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNestNav

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNestNav>(
        startDestination = CharacterDestination
    ) {
        CharactersScreen (
            onCharacterClick = { id -> navController.navigateToCharacterDetailsScreen(
                DetailDestination(id)
            ) }
        )
        DetailScreen (
            onNavigateBack = navController::navigateUp
        )

    }
}