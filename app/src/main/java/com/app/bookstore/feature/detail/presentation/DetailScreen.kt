package com.app.bookstore.feature.detail.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.networkstate.Resource

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun DetailScreen(viewModel: DetailViewModel, id: String, pressOnBack: () -> Unit) {
    LaunchedEffect(id) {
        viewModel.fetchVolumeIdById(id)
    }
    val detailData = viewModel.detailData.collectAsState(initial = null)
    Scaffold(
        topBar = {
            AppBar(detailData.value?.data?.volumeInfo?.title.orEmpty(), pressOnBack)
        }
    ) { innerPadding ->
        detailData.value?.status.run {
            when (this) {
                Resource.Status.SUCCESS -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item() {
                                detailData.value?.data?.run {

                                    BookImage(imageUrl = volumeInfo.imageLinks?.smallThumbnail.orEmpty())
                                    BookPrice(
                                        price = saleInfo.listPrice?.amount.toString()
                                            .orEmpty() + "" + saleInfo.listPrice?.currencyCode.orEmpty()
                                    )
                                    LinkView(saleInfo.buyLink, volumeInfo.previewLink)
                                    BookDescription(description = volumeInfo.description.orEmpty())
                                }
                            }
                        }
                    }
                }

                Resource.Status.LOADING -> LoadingItem()

                is Resource.Status.ERROR -> {
                    val error = detailData.value?.status as Resource.Status.ERROR
                    ErrorItem(
                        message = error.message,
                        modifier = Modifier,
                        onClickRetry = {})
                }

                else -> {}
            }
        }
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
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Fit,
        alignment = Alignment.Center
    )
}

@Composable
fun BookDescription(description: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AppCompatTextView(context).apply {
                setTextColor(context.resources.getColor(R.color.gray))
                setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.description_size))
            }
        },
        update = { it.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY) }
    )
}

@Composable
fun LinkView(buyLink: String, previewPdf: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val webIntent: Intent = Uri.parse(previewPdf).let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(context, webIntent, null)
            },
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape), shape = MaterialTheme.shapes.medium
        ) {
            Text(
                modifier = Modifier,
                text = "PreviewPdfLink",
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Clip,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
        }

        Button(
            onClick = {
                startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(buyLink)),
                    null
                )
            }, modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape), shape = MaterialTheme.shapes.medium
        ) {
            Text(
                modifier = Modifier,
                text = "BuyBookLink",
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Clip,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun BookPrice(price: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .padding(15.dp),
            elevation = 10.dp,
            backgroundColor = colorResource(id = R.color.purple_500)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = price,
                style = MaterialTheme.typography.button,
                overflow = TextOverflow.Clip,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun AppBar(
    title: String,
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