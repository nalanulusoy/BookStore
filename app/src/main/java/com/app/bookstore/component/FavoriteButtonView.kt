package com.app.bookstore.component

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 05,Temmuz,2022
 */
@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(R.color.purple_700),
    isChecked:Boolean = false
) : Boolean {
    var isFavorite by remember { mutableStateOf(isChecked) }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
 return isFavorite
}

@Preview("Favorite Button")
@Composable
fun FavoriteButtonPreview() {
    val (isChecked) = remember { mutableStateOf(false) }
    MaterialTheme {
        Surface {
            FavoriteButton(
                isChecked = isChecked
            )
        }
    }
}
