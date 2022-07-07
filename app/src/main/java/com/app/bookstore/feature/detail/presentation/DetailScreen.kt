package com.app.bookstore.feature.detail.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
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
import coil.compose.AsyncImage
import com.app.bookstore.BuildConfig
import com.app.bookstore.R
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun DetailScreen(viewModel: DetailViewModel, id: String, pressOnBack: () -> Unit) {
    val showLoading = remember { mutableStateOf(false) }
    if (showLoading.value) {
       LoadingItem()
    }
    LaunchedEffect(id) {
        viewModel.fetchVolumeIdById(id)
    }
    val detailData = viewModel.detailData.onStart {
        showLoading.value = true
    }.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white))
            .fillMaxSize(),
    ) {
            detailData.value?.run {
                AppBar(volumeInfo?.title.orEmpty(),pressOnBack)
                BookImage(imageUrl = volumeInfo.imageLinks?.smallThumbnail.orEmpty())
                BookPrice(price = saleInfo.listPrice?.amount.toString().orEmpty()+ "" +saleInfo.listPrice?.currencyCode.orEmpty())
                LinkView(saleInfo.buyLink,volumeInfo.previewLink)
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
            .fillMaxWidth()
            .fillMaxHeight(),
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
fun LinkView(buyLink: String,previewPdf:String){
 val context = LocalContext.current
       Row(modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight(),horizontalArrangement = Arrangement.Center){
           Button(
               onClick = {
                   val webIntent: Intent = Uri.parse(previewPdf).let { webpage ->
                       Intent(Intent.ACTION_VIEW, webpage)
                   }
                   startActivity(context, webIntent, null)
               },
               modifier = Modifier.padding(16.dp), shape = MaterialTheme.shapes.medium) {
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

           Button(onClick = { startActivity(context,Intent(Intent.ACTION_VIEW, Uri.parse(buyLink)),null) },modifier = Modifier.padding(16.dp), shape = MaterialTheme.shapes.medium) {
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
fun BookPrice(price:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .padding(15.dp)
            .background(colorResource(id = R.color.cardview_light_background)),
            elevation = 10.dp
        ){
            Text(
                modifier = Modifier.padding(16.dp),
                text = price,
                style = MaterialTheme.typography.button,
                overflow = TextOverflow.Clip,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
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