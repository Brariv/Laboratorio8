package com.uvg.laboratorio8.Layout.ProfileScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogoutClick: () -> Unit
) {
    composable<ProfileDestination> {
        ProfileScreen(
            onLogoutClick = onLogoutClick
        )
    }
}