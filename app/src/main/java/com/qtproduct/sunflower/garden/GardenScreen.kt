package com.qtproduct.sunflower.garden

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qtproduct.sunflower.model.Plant
import com.qtproduct.sunflower.model.SunflowerImage

/**
 * @author quangtran
 * @since 9/22/23
 */

@Composable
fun GardenScreen(
    modifier: Modifier = Modifier,
    viewModel: GardenViewModel = hiltViewModel()
) {
    GardenList(modifier = modifier, gardenPlants = viewModel.plants)
}


@Composable
fun GardenList(
    modifier: Modifier,
    gardenPlants: List<Plant>,
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 16.dp
        ),
        state = gridState,
    ) {
        items(
            items = gardenPlants,
            key = { it.id }
        ) {
            MyGardenItem(plant = it, onPlantClick = {})
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyGardenItem(
    plant: Plant,
    onPlantClick: (Plant) -> Unit,
) {
    ElevatedCard(
        onClick = { onPlantClick(plant) },
        modifier = Modifier.padding(
            start = 12.dp,
            end = 12.dp,
            bottom = 16.dp,
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(Modifier.fillMaxWidth()) {
            SunflowerImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp), model = plant.imgUrl
            )
        }
    }
}