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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bookstore.R
import com.google.accompanist.flowlayout.FlowColumn
import kotlinx.coroutines.flow.retry

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
        AppBar(pressOnBack)
        FlowColumn() {
            Text(
                text = detailData.value?.selfLink.orEmpty(),
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AppBar(
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
                text = stringResource(id = R.string.detail_title),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}