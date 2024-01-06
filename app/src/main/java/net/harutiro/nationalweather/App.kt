package net.harutiro.nationalweather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.harutiro.nationalweather.core.entities.BottomNavigationItem
import net.harutiro.nationalweather.features.favorite.page.FavoritePage
import net.harutiro.nationalweather.features.home.page.HomePage
import net.harutiro.nationalweather.ui.theme.NationalWeatherTheme
@Composable
fun App() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home,
            hasNews = false,
            badgeCount = null
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.favorite),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Filled.Favorite,
            hasNews = false,
            badgeCount = null
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                items = bottomNavigationItems,
                selectedItemIndex = selectedItemIndex
            ) { index ->
                selectedItemIndex = index
                navController.navigate(bottomNavigationItems[index].title)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = bottomNavigationItems[selectedItemIndex].title,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("home") {
                HomePage()
            }
            composable("favorite") {
                FavoritePage()
            }
        }
    }
}

@Composable
private fun NavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    BadgeIcon(
                        badgeCount = screen.badgeCount,
                        hasNews = screen.hasNews,
                        selectedIcon = screen.selectedIcon,
                        unselectedIcon = screen.unselectedIcon,
                        index = index,
                        selectedItemIndex = selectedItemIndex,
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) },
            )
        }
    }
}

@Composable
private fun BadgeIcon(
    badgeCount: Int?,
    hasNews: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    index: Int,
    selectedItemIndex: Int,
    contentDescription: String
) {
    BadgedBox(
        badge = {
            when {
                badgeCount != null -> {
                    Badge {
                        Text(text = badgeCount.toString())
                    }
                }
                hasNews -> {
                    Badge()
                }
            }
        }
    ) {
        Icon(
            imageVector = if (index == selectedItemIndex) selectedIcon else unselectedIcon,
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NationalWeatherTheme {
        App()
    }
}