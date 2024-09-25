package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.uvg.laboratorio8.Layout.MainScreen.MainScreenDestination

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
