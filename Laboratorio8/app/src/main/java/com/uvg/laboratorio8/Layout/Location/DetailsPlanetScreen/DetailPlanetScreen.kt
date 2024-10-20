package com.uvg.laboratorio8.Layout.DetailsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.Data.domain.model.Location
import LocationDb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationScreenEvent
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationState
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationViewModel
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun DetailPlanetScreenRoute(ID: Int,
                            viewModel: LocationViewModel = viewModel(factory = LocationViewModel.Factory),
                            onNavigateBack: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(ID) {
        viewModel.onEvent(LocationScreenEvent.onLocationClick(ID))

    }

    DetailPlanetScreen(state = state,
        onNavigateBack = onNavigateBack,
        onLoadingClick = { viewModel.onEvent(LocationScreenEvent.onLoadClick) },
        OnRetryClick = { viewModel.onEvent(LocationScreenEvent.onLocationClick(ID)) }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPlanetScreen(state: LocationState,
                       onLoadingClick: () -> Unit = {},
                       OnRetryClick: () -> Unit = {},
                       onNavigateBack: () -> Unit = {},
                       modifier: Modifier = Modifier
) {

    if (state.isLoading) {
        LoadingScreen(onLoadingClick = onLoadingClick)
        return
    }
    if (state.hasError) {
        ErrorScreen(OnRetryClick = OnRetryClick)
        return
    }

    Column (modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                Row(modifier = Modifier.fillMaxWidth()
                    ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null,
                        modifier = Modifier.clickable { onNavigateBack() })
                    Spacer(modifier = Modifier.padding(8.dp, 0.dp))
                    Text("Location Details")
                }
            },
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                scrolledContainerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp)
            , horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = state.location?.name ?:"", style = MaterialTheme.typography.titleLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(modifier = Modifier.padding(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "ID:")
                Text(text = state.location?.id.toString())
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Type:")
                Text(text = state.location?.type ?:"")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp)
            , horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Dimensions:")
                Text(text = state.location?.dimension ?:"")
            }


        }



    }

}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier,
                OnRetryClick: () -> Unit){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon( Icons.Default.Info,
            contentDescription = "Error",
            modifier = Modifier.size(75.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Error al obtener informacion de la ubicacion", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
        Text(text = "Intente de nuevo", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedButton(onClick = OnRetryClick) {
            Text("Reintentar", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier,
                  onLoadingClick: () -> Unit){
    Column (modifier = Modifier
        .fillMaxSize()
        .clickable { onLoadingClick() }
        , verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally

    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text("Cargando",style = MaterialTheme.typography.titleMedium)

    }
}
/*

@Preview
@Composable
fun MainScreenPreview() {
    Laboratorio8Theme {
        Surface {
            PlanetData(ID = 1, onNavigateBack = { /*TODO*/ })
        }
    }
}

 */