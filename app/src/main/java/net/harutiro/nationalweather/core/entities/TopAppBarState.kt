package net.harutiro.nationalweather.core.entities

data class TopAppBarState(
    val title: String,
    val showBackButton: Boolean,
    val onBackClick: (() -> Unit)? = null,
)
