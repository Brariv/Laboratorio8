package com.uvg.laboratorio8

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.uvg.laboratorio8.Layout.MainScreen.AppContent
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginViewModel
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginStatus

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            loginViewModel.authStatus.value is LoginStatus.Loading
        }

        
        setContent {
            Laboratorio8Theme {
                Surface {
                    AppContent(loginViewModel)
                }

            }
        }
    }

}
/*
Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ){
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
    }
}

 */