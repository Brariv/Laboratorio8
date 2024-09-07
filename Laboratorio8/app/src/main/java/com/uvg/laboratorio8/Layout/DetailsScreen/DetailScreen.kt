package com.uvg.laboratorio8.Layout.DetailsScreen

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.laboratorio8.Data.Character
import com.uvg.laboratorio8.Data.CharacterDb
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun DetailScreenRoute(ID: Int, onNavigateBack: () -> Unit , modifier: Modifier = Modifier) {
    CharacterData(ID = ID, onNavigateBack = onNavigateBack, modifier = modifier)

}

@Composable
fun CharacterData(ID: Int, onNavigateBack: () -> Unit, modifier: Modifier = Modifier){
    val characterinfo = CharacterDb().getCharacterById(ID)
    DetailScreen(Model = characterinfo.image, Name = characterinfo.name,
        Species = characterinfo.species, Status = characterinfo.status, Gender = characterinfo.gender,
        onNavigateBack = onNavigateBack,
        modifier = Modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(Model: String, Name: String, Species: String, Status: String, Gender: String,
                 onNavigateBack: () -> Unit = {},
                 modifier: Modifier = Modifier
) {
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
                    model = Model,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = Name, style = MaterialTheme.typography.titleLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)


            Spacer(modifier = Modifier.padding(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Species:")
                Text(text = Species)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Status:")
                Text(text = Status)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp)
            , horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Gender:")
                Text(text = Gender)
            }


        }



    }

}

@Preview
@Composable
fun MainScreenPreview() {
    Laboratorio8Theme {
        Surface {
            CharacterData(ID = 1, onNavigateBack = { /*TODO*/ })
        }
    }
}