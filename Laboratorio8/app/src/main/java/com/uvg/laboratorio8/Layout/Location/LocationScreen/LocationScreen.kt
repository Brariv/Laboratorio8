package com.uvg.laboratorio8.Layout.CharactersScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationScreenEvent
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationState
import com.uvg.laboratorio8.Layout.Location.ViewModel.LocationViewModel

@Composable
fun LocationRoute(onLocationClick: (Int) -> Unit,
                  viewModel: LocationViewModel = viewModel(factory = LocationViewModel.Factory),
                  modifier: Modifier = Modifier

){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(LocationScreenEvent.onLocationListClick)
    }

    LocationScreen(
        state = state,
        onLoadingClick = { viewModel.onEvent(LocationScreenEvent.onLoadClick)},
        OnRetryClick = { viewModel.onEvent(LocationScreenEvent.onLocationListClick)},
        onLocationClick = onLocationClick
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(state: LocationState,
                   onLoadingClick: () -> Unit,
                   OnRetryClick: () -> Unit,
                   onLocationClick: (Int) -> Unit,
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
            title = { Text("Locations") }, colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                scrolledContainerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(40.dp)
        ) {
            items(items = state.locationList) { location ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLocationClick(location.id) }) {
                    LocationRow(
                        Name = location.name,
                        Type = location.type,
                        Dimension = location.dimension
                    )


                }


            }
        }
    }

}

@Composable
private fun LocationRow(Name: String, Type: String, Dimension: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Column (verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
            Text(text = Name, style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text(text = Type + " - " + Dimension, style = MaterialTheme.typography.bodyMedium)

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
        Text(text = "Error al obtener ubicaciones", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
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
fun LocationScreenPreview() {
    Laboratorio8Theme {
        Surface {
            LocationData(onLocationClick = {},
                modifier = Modifier.fillMaxSize())
        }
    }
}

 */