package com.uvg.laboratorio8.Layout.DetailsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uvg.laboratorio8.Layout.CharactersScreen.CharacterRoute
import com.uvg.laboratorio8.Layout.CharactersScreen.CharactersDestination
import kotlinx.serialization.Serializable

@Serializable
data class DetailDestination(
    val ID: Int
)


fun NavController.navigateToCharacterDetailsScreen(
    destination: DetailDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.DetailScreen(
    onNavigateBack: () -> Unit
) {
    composable<DetailDestination> { backStackEntry ->
        val destination: DetailDestination = backStackEntry.toRoute()
        DetailScreenRoute(ID = destination.ID,
            onNavigateBack = onNavigateBack,
            modifier = Modifier.fillMaxWidth())
    }
}