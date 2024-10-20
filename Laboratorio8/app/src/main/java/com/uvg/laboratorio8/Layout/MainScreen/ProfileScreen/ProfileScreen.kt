package com.uvg.laboratorio8.Layout.MainScreen.ProfileScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginEvent
import com.uvg.laboratorio8.Layout.MainScreen.ViewModel.LoginViewModel
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun ProfileROute(
    onLogoutClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {

    val userNameState by viewModel.userNameState.collectAsStateWithLifecycle()

    ProfileScreen(
        onLogoutClick = {
            onLogoutClick()
            viewModel.onEvent(LoginEvent.DeleteName)
        },
        userNameState = userNameState

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onLogoutClick: () -> Unit,
                  userNameState: String?


) {

    Column(modifier = Modifier.fillMaxSize()) {
    TopAppBar(
        title = { Text("Profile") }, colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(250.dp),
                colorResource(
                    id = android.R.color.white
                )
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 8.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text(text = "Nombre: ")
            Text(text = if (userNameState != null && userNameState != "") userNameState else "Unknown")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 8.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text(text = "Canre: ")
            Text(text = "23088")
        }
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedButton(onClick = { onLogoutClick() }) {
            Text(text = "Cerrar Sesion")
        }


    }
}




}

@Preview
@Composable
fun MainScreenPreview() {
    Laboratorio8Theme {
        Surface {
            ProfileScreen(onLogoutClick = {},
            userNameState = "Brandon Rivera"
            )
        }
    }
}