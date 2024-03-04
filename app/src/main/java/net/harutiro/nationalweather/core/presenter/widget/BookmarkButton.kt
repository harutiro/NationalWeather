package net.harutiro.nationalweather.core.presenter.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookmarkButton(
    isBookmark: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = if (isBookmark) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun BookmarkButtonPreview() {

    val isBookmark = remember {
        mutableStateOf(false)
    }

    BookmarkButton(
        isBookmark = isBookmark.value,
        onClick = {
            isBookmark.value = !isBookmark.value
        }
    )
}