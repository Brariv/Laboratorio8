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
import Location
import LocationDb
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun DetailPlanetScreenRoute(ID: Int, onNavigateBack: () -> Unit) {
    PlanetData(ID = ID, onNavigateBack = onNavigateBack, modifier = Modifier.fillMaxWidth())

}

@Composable
fun PlanetData(ID: Int, onNavigateBack: () -> Unit, modifier: Modifier = Modifier){
    val locationinfo = LocationDb().getLocationById(ID)
    DetailPlanetScreen(Name = locationinfo.name,
        Type = locationinfo.type, Dimension = locationinfo.dimension, ID = locationinfo.id,
        onNavigateBack = onNavigateBack,
        modifier = Modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPlanetScreen( Name: String, Type: String, Dimension: String, ID : Int,
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
            Text(text = Name, style = MaterialTheme.typography.titleLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(modifier = Modifier.padding(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "ID:")
                Text(text = ID.toString())
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Type:")
                Text(text = Type)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 8.dp)
            , horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
                Text(text = "Dimensions:")
                Text(text = Dimension)
            }


        }



    }

}

@Preview
@Composable
fun MainScreenPreview() {
    Laboratorio8Theme {
        Surface {
            PlanetData(ID = 1, onNavigateBack = { /*TODO*/ })
        }
    }
}