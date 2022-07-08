package com.app.bookstore.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 08,Temmuz,2022
 */
@Composable
fun BookImageView(
    imageUrl: String,
    contentDescription: String = "review",
    alignment: Alignment = Alignment.Center,
    error: Painter? = painterResource(R.drawable.ic_broken_image),
    modifier: Modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        error = error,
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment
    )
}

@Preview("BookImageView")
@Composable
fun BookImageViewPreview() {
    MaterialTheme {
        Surface {
            BookImageView(
                imageUrl = "http://books.google.com/books/content?id=r_ATi4W935cC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
            )
        }
    }
}
