package com.app.bookstore.feature.detail.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.bookstore.R

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */


@Composable
fun DetailScreen(viewModel: DetailViewModel, id: String, pressOnBack: () -> Unit) {
    LaunchedEffect(id) {
        viewModel.fetchVolumeIdById(id)
    }
    val detailData = viewModel.detailData.collectAsState(initial = null)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white))
            .fillMaxSize(),
    ) {
            detailData.value?.run {
                AppBar(volumeInfo?.title.orEmpty(),pressOnBack)
                BookImage(imageUrl = volumeInfo.imageLinks?.thumbnail.orEmpty())
                BookDescription(description = volumeInfo.description.orEmpty())
            }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
@Composable
fun BookImage(
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Book Review",
        error = painterResource(R.drawable.ic_broken_image),
        modifier = Modifier
            .padding(16.dp)
            .width(100.dp)
            .height(100.dp),
        contentScale = ContentScale.Fit,
        alignment = Alignment.Center
    )
}

@Composable
fun BookDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.caption,
        overflow = TextOverflow.Clip,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun AppBar(
    title:String,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = colorResource(id = R.color.purple_500),
        modifier = Modifier.height(60.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                imageVector = Icons.Filled.ArrowBack,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        pressOnBack()
                    }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}