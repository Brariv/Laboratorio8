package com.uvg.laboratorio8.Layout.MainScreen.LoginScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginEvent
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginState
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginViewModel
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme



@Composable
fun MainScreenRoute(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state,
        onLoginClick = {
        onLoginClick()
        viewModel.onEvent(LoginEvent.SaveName)
        },
        onNameChange = {viewModel.onEvent(LoginEvent.NameChange(it))},
        modifier = modifier
    )
}

@Composable
fun MainScreen(onLoginClick: () -> Unit ,onNameChange:(String) -> Unit ,modifier: Modifier = Modifier, state: LoginState) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(0.8f)
            ) {
                AsyncImage(
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRloL9IMCEVibQdaRLVo2ou2tdbLs8QxHasvw&s",
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(value = state.name, onValueChange = onNameChange, modifier = Modifier.fillMaxWidth(0.8f), placeholder = { Text("Name") })
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { onLoginClick() }, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(text = "Iniciar Sesion")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(text = "Brandon Rivera - 23088")
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    Laboratorio8Theme {
        Surface {
            MainScreen(onLoginClick = {},
                onNameChange = {},
                modifier = Modifier.fillMaxSize(),
                state = LoginState()

            )
        }
    }
}