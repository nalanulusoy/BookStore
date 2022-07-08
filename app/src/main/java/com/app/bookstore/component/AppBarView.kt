package com.app.bookstore.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 08,Temmuz,2022
 */
@Composable
fun AppBarView(
    title: String,
    pressOnBack: (() -> Unit?)? = null,
    colorTitle : Color = Color.White,
    titleFontSize: TextUnit = 18.sp,
    backgroundColor: Color = colorResource(id = R.color.purple_500),
    visibleButton : Boolean  = false
) {
    var visibleBackButton by remember { mutableStateOf(visibleButton) }

    TopAppBar(
        elevation = 6.dp,
        backgroundColor = backgroundColor,
        modifier = Modifier.height(60.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            if(visibleBackButton){
                Image(
                    imageVector = Icons.Filled.ArrowBack,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (pressOnBack != null) {
                                pressOnBack()
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                text = title,
                color = colorTitle,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Preview("App Bar View")
@Composable
fun AppBarViewPreview() {
    MaterialTheme {
        Surface {
            AppBarView(
                "Book Detail",
                 pressOnBack = { }
            )
        }
    }
}