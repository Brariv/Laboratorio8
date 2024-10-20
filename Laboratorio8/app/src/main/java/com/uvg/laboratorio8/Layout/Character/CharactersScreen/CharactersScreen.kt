package com.uvg.laboratorio8.Layout.Character.CharactersScreen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.laboratorio8.Data.domain.model.Character
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterScreenEvent
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterState
import com.uvg.laboratorio8.Layout.Character.ViewModel.CharacterViewModel

@Composable
fun CharacterRoute(onCharacterClick: (Int) -> Unit
                   ,viewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(CharacterScreenEvent.onCharacterListClick)

    }

    CharactersScreen(onCharacterClick = onCharacterClick,
        onLoadingClick = { viewModel.onEvent(CharacterScreenEvent.onLoadClick)},
        OnRetryClick = {viewModel.onEvent(CharacterScreenEvent.onCharacterListClick)},
        state = state,
        modifier = Modifier.fillMaxWidth())
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(state: CharacterState,
                     onCharacterClick: (Int) -> Unit,
                     modifier: Modifier = Modifier,
                     onLoadingClick: () -> Unit = {},
                     OnRetryClick: () -> Unit = {}

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
            title = { Text("Characters") }, colors = TopAppBarColors(
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
            items(items = state.characterList) { character: Character ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClick(character.id) }) {
                    CharacterRow(
                        Name = character.name,
                        Species = character.species,
                        Status = character.status,
                        Gender = character.gender,
                        Model = character.image
                    )


                }


            }
        }
    }

}

@Composable
private fun CharacterRow(Name: String, Species: String, Status: String, Gender: String, Model: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
            .size(64.dp)
            .clip(CircleShape)
        ){
            AsyncImage(
                model = Model,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Column (verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
            Text(text = Name, style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text(text = Species + " - " + Status + " - " + Gender, style = MaterialTheme.typography.bodyMedium)

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
        Text(text = "Error al obtener listado de personajes", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
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
            CharactersData(onCharacterClick = {},
                modifier = Modifier.fillMaxSize())
        }
    }
}



 */