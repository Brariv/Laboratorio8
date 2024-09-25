package com.uvg.laboratorio8.Layout.LocationScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.uvg.laboratorio8.Layout.CharactersScreen.LocationDestination
import com.uvg.laboratorio8.Layout.CharactersScreen.LocationsScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailDestination
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailPlanetDestination
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailPlanetScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.DetailScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.navigateToCharacterDetailsScreen
import com.uvg.laboratorio8.Layout.DetailsScreen.navigateToLocationDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocationNestNav

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNestNav>(
        startDestination = LocationDestination
    ) {
        LocationsScreen (
            onLocationClick = { id -> navController.navigateToLocationDetailsScreen(
                DetailPlanetDestination(id)
            ) }
        )
        DetailPlanetScreen (
            onNavigateBack = navController::navigateUp
        )

    }
}