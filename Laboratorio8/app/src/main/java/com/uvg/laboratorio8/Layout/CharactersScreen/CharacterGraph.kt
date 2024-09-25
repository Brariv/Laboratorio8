package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailDestination
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.navigateToCharacterDetailsScreen
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