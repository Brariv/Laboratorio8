package com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.Layout.Character.CharacterNestNav
import com.uvg.laboratorio8.Layout.Character.characterGraph
import com.uvg.laboratorio8.Layout.Location.locationGraph
import com.uvg.laboratorio8.Layout.BottomBar.ProfileScreen.profileScreen


@Composable
fun bottomBarNavigation(
    onNavItemClick: (Any) -> Unit,
    checkItemSelected: (Any) -> Boolean
){
    NavigationBar {
        BottomNavigationItems.forEach { NavigationItem ->
            val isItemSelected = checkItemSelected(NavigationItem.destination)
            NavigationBarItem(
                selected =  isItemSelected,
                onClick = {
                    onNavItemClick(NavigationItem.destination)
                },
                label = { Text(text = NavigationItem.title) },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) NavigationItem.selectedIcon else NavigationItem.unselectedIcon,
                        contentDescription = NavigationItem.title
                    )

                }
            )
        }
    }
}

@Composable
fun BottomBarScreen(
    onLogoutClick: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    var bottomBarVisibility by rememberSaveable { mutableStateOf(false) }
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    bottomBarVisibility = if (currentDestination != null) {
        topLevelDestinations.any { destination ->
            currentDestination.hasRoute(destination)
        }
    } else {
        false
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                bottomBarNavigation(
                    onNavItemClick = { destination ->
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }


                    },
                    checkItemSelected = { destination ->
                        navController.currentDestination?.hierarchy?.any { it.route == destination }
                            ?: false
                    }
                )
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharacterNestNav,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            characterGraph(navController)
            locationGraph(navController)
            profileScreen(
                onLogoutClick = onLogoutClick
            )

        }
    }
}








