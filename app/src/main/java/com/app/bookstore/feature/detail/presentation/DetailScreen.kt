package com.app.bookstore.feature.detail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.extention.BookBuyLink
import com.app.bookstore.base.extention.BookPdfLink
import com.app.bookstore.base.extention.EMPTY
import com.app.bookstore.base.networkstate.Resource
import com.app.bookstore.component.AppBarView
import com.app.bookstore.component.BookImageView
import com.app.bookstore.component.HtmlTextView
import com.app.bookstore.component.ShapeButtonView
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.detail.data.response.VolumeDetailResponse

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun DetailScreen(viewModel: DetailViewModel, id: String, pressOnBack: () -> Unit) {
    LaunchedEffect(id) {
        viewModel.fetchVolumeIdById(id)
    }
    val detailData = viewModel.detailData.collectAsState(initial = null)
    Scaffold(
        topBar = {
            AppBarView(
                detailData.value?.data?.volumeInfo?.title.orEmpty(),
                pressOnBack,
                visibleButton = true
            )
        }
    ) { innerPadding ->
        detailData?.value?.status.run {
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
                                    BookImageView(imageUrl = volumeInfo.imageLinks?.smallThumbnail.orEmpty())
                                    BookPrice(
                                        price = saleInfo.listPrice?.amount.toString()
                                            .orEmpty() + EMPTY + saleInfo.listPrice?.currencyCode.orEmpty()
                                    )
                                    LinkView(saleInfo.buyLink, volumeInfo.previewLink)
                                    HtmlTextView(description = volumeInfo.description.orEmpty())
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
fun LinkView(buyLink: String, previewPdf: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalArrangement = Arrangement.Center
    ) {
        ShapeButtonView(url = previewPdf, text = BookPdfLink)
        ShapeButtonView(url = buyLink, text = BookBuyLink)
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
                modifier = Modifier.padding(8.dp),
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
