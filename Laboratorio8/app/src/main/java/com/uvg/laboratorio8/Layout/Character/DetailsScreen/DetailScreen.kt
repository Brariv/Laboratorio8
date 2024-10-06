package com.uvg.laboratorio8.Layout.Character.DetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterScreenEvent
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterState
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterViewModel

@Composable
fun DetailScreenRoute(ID: Int,
                      viewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory),
                      onNavigateBack: () -> Unit ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(ID) {
        viewModel.onEvent(CharacterScreenEvent.onCharacterClick(ID))

    }

    DetailScreen(state = state,
        onNavigateBack = onNavigateBack,
        onLoadingClick = { viewModel.onEvent(CharacterScreenEvent.onLoadClick) },
        OnRetryClick = { viewModel.onEvent(CharacterScreenEvent.onCharacterClick(ID)) }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(state: CharacterState,
                 onNavigateBack: () -> Unit = {},
                 onLoadingClick: () -> Unit = {},
                 OnRetryClick: () -> Unit = {},
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
                    Text("Character Details")
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
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )

                    .size(220.dp)
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = state.character?.image,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = state.character?.name ?: "", style = MaterialTheme.typography.titleLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)


            Spacer(modifier = Modifier.padding(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Species:")
                Text(text = state.character?.species ?: "")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Status:")
                Text(text = state.character?.status ?: "")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp)
            , horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Gender:")
                Text(text = state.character?.gender ?: "")
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
        Text(text = "Error al obtener informacion del personaje", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
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
            CharacterData(ID = 1, onNavigateBack = { /*TODO*/ })
        }
    }
}

 */