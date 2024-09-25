package com.uvg.laboratorio8.Layout.DetailsScreen


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class DetailPlanetDestination(
    val ID: Int
)


fun NavController.navigateToLocationDetailsScreen(
    destination: DetailPlanetDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.DetailPlanetScreen(
    onNavigateBack: () -> Unit
) {
    composable<DetailPlanetDestination> { backStackEntry ->
        val destination: DetailPlanetDestination = backStackEntry.toRoute()
        DetailPlanetScreenRoute(ID = destination.ID,
            onNavigateBack = onNavigateBack)
    }
}