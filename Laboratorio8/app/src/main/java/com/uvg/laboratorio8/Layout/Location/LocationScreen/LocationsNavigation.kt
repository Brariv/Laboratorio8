package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data object LocationDestination


fun NavGraphBuilder.LocationsScreen(
    onLocationClick: (Int) -> Unit
) {
    composable<LocationDestination> { backStackEntry ->
        val destination: LocationDestination = backStackEntry.toRoute()
        LocationRoute(
            onLocationClick = onLocationClick
        )
    }
}
