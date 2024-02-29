package net.harutiro.nationalweather.core.router

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.harutiro.nationalweather.R
import net.harutiro.nationalweather.core.entities.BottomNavigationItem
import net.harutiro.nationalweather.core.presenter.BottomNavigationBar
import net.harutiro.nationalweather.core.utils.DateUtils
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.core.presenter.favorite.page.FavoritePage
import net.harutiro.nationalweather.core.presenter.home.page.HomePage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBarRouter(toDetail: (cityId: CityId) -> Unit){
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home,
            hasNews = false,
            badgeCount = null,
            path = BottomNavigationBarRoute.HOME
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.favorite),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Filled.Favorite,
            hasNews = false,
            badgeCount = null,
            path = BottomNavigationBarRoute.FAVORITE
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                val now = DateUtils.getNowString()
                Text(text = "$now の全国天気") // 今日の日付(曜日)　の天気(

            })
        },
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                selectedItemIndex = selectedItemIndex
            ) { index ->
                selectedItemIndex = index
                navController.navigate(bottomNavigationItems[index].path.route)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = bottomNavigationItems[selectedItemIndex].path.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(BottomNavigationBarRoute.HOME.route) {
                HomePage(
                    toDetail = toDetail
                )
            }
            composable(BottomNavigationBarRoute.FAVORITE.route) {
                FavoritePage()
            }
        }
    }
}

enum class BottomNavigationBarRoute(val route: String) {
    HOME("home"),
    FAVORITE("favorite")
}

