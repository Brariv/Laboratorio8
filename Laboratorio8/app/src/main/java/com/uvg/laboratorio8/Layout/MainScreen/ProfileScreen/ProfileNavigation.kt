package com.uvg.laboratorio8.Layout.MainScreen.ProfileScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogoutClick: () -> Unit
) {
    composable<ProfileDestination> {
        ProfileROute(
            onLogoutClick = onLogoutClick
        )
    }
}