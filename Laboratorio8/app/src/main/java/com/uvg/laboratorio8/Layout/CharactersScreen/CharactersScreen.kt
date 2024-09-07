package com.uvg.laboratorio8.Layout.CharactersScreen

import android.graphics.ColorSpace.Model
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.laboratorio8.Data.Character
import com.uvg.laboratorio8.Data.CharacterDb
import com.uvg.laboratorio8.Layout.DetailsScreen.CharacterData
import com.uvg.laboratorio8.Layout.MainScreen.MainScreen
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.compose.ui.Alignment

@Composable
fun CharacterRoute(onCharacterClick: (Int) -> Unit,
                     modifier: Modifier = Modifier

){
    CharactersData(onCharacterClick = onCharacterClick,
        modifier = modifier)
}

@Composable
fun CharactersData(onCharacterClick: (Int) -> Unit, modifier: Modifier = Modifier){
    val characters by lazy{
        CharacterDb().getAllCharacters()
    }
    CharactersScreen(characters = characters, onCharacterClick = onCharacterClick, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(characters: List<Character>,
                        onCharacterClick: (Int) -> Unit,
                       modifier: Modifier = Modifier

) {
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
            items(items = characters) { character ->
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