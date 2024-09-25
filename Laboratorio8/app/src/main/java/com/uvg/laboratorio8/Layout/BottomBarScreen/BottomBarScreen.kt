package com.uvg.laboratorio8.Layout.BottomBarScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.Layout.CharactersScreen.CharacterNestNav
import com.uvg.laboratorio8.Layout.CharactersScreen.characterGraph
import com.uvg.laboratorio8.Layout.LocationScreen.locationGraph
import com.uvg.laboratorio8.Layout.ProfileScreen.profileScreen


@Composable
fun bottomBarNavigation(
    onNavItemClick: (Any) -> Unit,
    checkItemSelected: (Any) -> Boolean
){
    NavigationBar {
        BottomNavigationItems.forEach { BottomNavigationItem ->
            val isItemSelected = checkItemSelected(BottomNavigationItem.destination)
            NavigationBarItem(
                selected =  isItemSelected,
                onClick = {
                    onNavItemClick(BottomNavigationItem.destination)
                },
                label = { Text(text = BottomNavigationItem.title) },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) BottomNavigationItem.selectedIcon else BottomNavigationItem.unselectedIcon,
                        contentDescription = BottomNavigationItem.title
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

    /*
    var barVIibility by rememberSaveable {
        mutableIntStateOf(false)
    }

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    barVIibility = if(currentDestination != null){
        topLevelDestination.any { destination ->
            currentDestination.hasRoute(destination)

    }
    */

    /*
    AnimatedVisibility(visible = barVIibility,
            enter = slideInVertically { initialOffserY = { it }},
                exit = slideInVertically { targetOffsetY = { it }}
            ){
     */

    Scaffold(
        bottomBar = {
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
                    navController.currentDestination?.hierarchy?.any { it.route == destination}
                        ?: false
                }
            )
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






