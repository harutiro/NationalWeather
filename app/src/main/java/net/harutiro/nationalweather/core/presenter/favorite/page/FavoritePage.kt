package net.harutiro.nationalweather.core.presenter.favorite.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import net.harutiro.nationalweather.core.presenter.favorite.viewModel.FavoriteViewModel
import net.harutiro.nationalweather.features.Weather.entities.CityId

@Composable
fun FavoritePage(viewModel: FavoriteViewModel = viewModel()) {
    Column {
        Button(
            onClick = {
                viewModel.getAll()
            }
        ){
            Text("Get All")
        }
        Button(
            onClick = {
                viewModel.insert(CityId.tokyo)
            }
        ){
            Text("Insert")
        }
        Button(
            onClick = {
                viewModel.delete(CityId.tokyo)
            }
        ){
            Text("Delete")
        }
    }
}