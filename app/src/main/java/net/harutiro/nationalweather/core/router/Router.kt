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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.harutiro.nationalweather.R
import net.harutiro.nationalweather.core.entities.BottomNavigationItem
import net.harutiro.nationalweather.core.presenter.BottomNavigationBar
import net.harutiro.nationalweather.core.presenter.detail.page.DetailPage
import net.harutiro.nationalweather.core.presenter.favorite.page.FavoritePage
import net.harutiro.nationalweather.core.presenter.home.page.HomePage
import net.harutiro.nationalweather.core.utils.DateUtils
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepository
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepositoryImpl
import net.harutiro.nationalweather.features.weather.entities.CityId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Router(
    viewModel: RouterViewModel = viewModel(),
    weatherFavoriteRepository: WeatherFavoriteRepository = WeatherFavoriteRepositoryImpl(),
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var changedTopAppBarContent: @Composable () -> Unit by remember { mutableStateOf({}) }

    val bottomNavigationItems =
        listOf(
            BottomNavigationItem(
                title = stringResource(id = R.string.home),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Filled.Home,
                hasNews = false,
                badgeCount = null,
                path = BottomNavigationBarRoute.HOME,
            ),
            BottomNavigationItem(
                title = stringResource(id = R.string.favorite),
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Filled.Favorite,
                hasNews = false,
                badgeCount = null,
                path = BottomNavigationBarRoute.FAVORITE,
            ),
        )

    var selectedRoute by remember { mutableStateOf(BottomNavigationBarRoute.HOME) }

    LaunchedEffect(weatherFavoriteRepository) {
        if (!viewModel.isStarted.value) {
            val favoriteList = weatherFavoriteRepository.getFavoriteList().await()
            if (favoriteList.isNotEmpty()) {
                selectedRoute = BottomNavigationBarRoute.FAVORITE
            }
            viewModel.isStarted.value = true
        }
    }

    Scaffold(
        topBar = {
            val currentRoute = navBackStackEntry?.destination?.route
            when (currentRoute?.split("/")?.getOrNull(0)) {
                BottomNavigationBarRoute.DETAIL.route -> {
                    changedTopAppBarContent()
                }
                else -> {
                    TopAppBar(title = {
                        val now = DateUtils.getNowString()
                        Text(text = "$now の全国天気")
                    })
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                selectedItemIndex = bottomNavigationItems.indexOfFirst { it.path == selectedRoute },
            ) { index ->
                selectedRoute = bottomNavigationItems[index].path
                navController.navigate(selectedRoute.route)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = selectedRoute.route,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            composable(BottomNavigationBarRoute.HOME.route) {
                HomePage(toDetail = { cityId ->
                    navController.navigate("${BottomNavigationBarRoute.DETAIL.route}/${cityId.id}")
                })
            }
            composable(BottomNavigationBarRoute.FAVORITE.route) {
                FavoritePage()
            }
            composable(
                BottomNavigationBarRoute.DETAIL.route + "/{cityId}",
                arguments = listOf(navArgument("cityId") { type = NavType.StringType }),
            ) {
                val cityId = CityId.idToCityId(it.arguments?.getString("cityId") ?: "")
                if (cityId != null) {
                    DetailPage(
                        cityId = cityId,
                        toBackPage = { navController.popBackStack() },
                        topAppBarChanged = { content -> changedTopAppBarContent = content },
                    )
                }
            }
        }
    }
}

enum class BottomNavigationBarRoute(val route: String) {
    HOME("home"),
    FAVORITE("favorite"),
    DETAIL("detail"),
}
