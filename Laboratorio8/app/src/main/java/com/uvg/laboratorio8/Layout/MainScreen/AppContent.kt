package com.uvg.laboratorio8.Layout.MainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.BottomBarScreenDestination
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.navigateToBottomBarGraph
import com.uvg.laboratorio8.Layout.BottomBar.BottomBarScreen.navigateToBottomBarScreen
import com.uvg.laboratorio8.Layout.MainScreen.LoginScreen.MainScreen
import com.uvg.laboratorio8.Layout.MainScreen.LoginScreen.MainScreenDestination
import com.uvg.laboratorio8.Layout.MainScreen.ProfileScreen.ProfileDestination
import com.uvg.laboratorio8.Layout.MainScreen.ProfileScreen.profileScreen
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginStatus
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginViewModel
import kotlinx.serialization.Serializable

@Serializable
data object StartDestination

@Composable
fun AppContent(
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
){
    val navController = rememberNavController()
    val Status by viewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = StartDestination
    ){
        composable<StartDestination>{

        }
        MainScreen(
                onLoginClick = viewModel::logIn
            )

        navigateToBottomBarScreen(
            onLogoutClick = viewModel::logOut
        )

    }

    LaunchedEffect(Status){
        when(Status){
            LoginStatus.Authenticated -> {
                navController.navigateToBottomBarGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<StartDestination>(
                        inclusive = true
                    ).build()
                )
            }
            LoginStatus.NonAuthenticated -> {
                navController.navigate(MainScreenDestination){
                    popUpTo(0)
                }
            }
            LoginStatus.Loading -> {}
        }

    }


}

/*
MainScreen(onLoginClick = {
    navController.navigateToBottomBarGraph(
        navOptions = NavOptions.Builder().setPopUpTo<MainScreenDestination>(
            inclusive = true
        ).build()
    )
})
navigateToBottomBarScreen(
onLogoutClick = {
    navController.navigate(MainScreenDestination){
        popUpTo(0)
    }
},
)
*



 */