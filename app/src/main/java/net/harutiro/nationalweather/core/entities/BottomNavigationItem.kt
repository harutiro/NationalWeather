package net.harutiro.nationalweather.core.entities

import androidx.compose.ui.graphics.vector.ImageVector
import net.harutiro.nationalweather.core.router.BottomNavigationBarRoute

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val path: BottomNavigationBarRoute
)