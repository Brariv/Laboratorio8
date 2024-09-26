package com.uvg.laboratorio8.Layout.BottomBarScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.laboratorio8.Layout.CharactersScreen.CharacterNestNav
import com.uvg.laboratorio8.Layout.LocationScreen.LocationNestNav
import com.uvg.laboratorio8.Layout.ProfileScreen.ProfileDestination

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any
)

val BottomNavigationItems = listOf(
    NavigationItem(
        title = "Home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        destination = CharacterNestNav
    ),
    NavigationItem(
        title = "Location",
        unselectedIcon = Icons.Outlined.LocationOn,
        selectedIcon = Icons.Filled.LocationOn,
        destination = LocationNestNav
    ),
    NavigationItem(
        title = "Profile",
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        destination = ProfileDestination
    )
)
