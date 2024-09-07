package com.uvg.laboratorio8.Layout.MainScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MainScreenDestination

fun NavGraphBuilder.MainScreen(
    onLoginClick: () -> Unit
) {
    composable<MainScreenDestination> {
        MainScreenRoute(
            onLoginClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}