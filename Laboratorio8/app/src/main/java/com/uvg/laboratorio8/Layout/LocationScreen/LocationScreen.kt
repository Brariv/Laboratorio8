package com.uvg.laboratorio8.Layout.CharactersScreen

import Location
import LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.compose.ui.Alignment

@Composable
fun LocationRoute(onLocationClick: (Int) -> Unit,
                     modifier: Modifier = Modifier

){
    LocationData(onLocationClick = onLocationClick,
        modifier = modifier)
}

@Composable
fun LocationData(onLocationClick: (Int) -> Unit, modifier: Modifier = Modifier){
    val location by lazy{
        LocationDb().getAllLocations()
    }
    LocationScreen(location = location, onLocationClick = onLocationClick, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(location: List<Location>,
                     onLocationClick: (Int) -> Unit,
                       modifier: Modifier = Modifier

) {
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
            items(items = location) { location ->
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