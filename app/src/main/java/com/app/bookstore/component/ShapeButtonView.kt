package com.app.bookstore.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bookstore.R
import com.app.bookstore.base.extention.BookPdfLink
import com.app.bookstore.base.extention.openIntentActionView


/**
 * Created by Nalan Ulusoy on 08,Temmuz,2022
 */
@Composable
fun ShapeButtonView(
    url:String?= null,
    onClick: (() -> Unit?)? = null,
    text: String,
    fontSize: TextUnit = 16.sp,
    textColor: Color = colorResource(id = R.color.white),
    shape: Shape = CircleShape
) {
  val context = LocalContext.current
    Button(
        onClick = {
            if(!url.isNullOrEmpty()){
                context.openIntentActionView(url)
            }
            else{
                onClick
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .clip(shape),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            modifier = Modifier,
            text = text,
            style = MaterialTheme.typography.caption,
            overflow = TextOverflow.Clip,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}


@Preview("ShapeButtonView")
@Composable
fun ShapeButtonViewPreview() {
    MaterialTheme {
        Surface {
            ShapeButtonView(
                text = BookPdfLink,
                onClick = {}
            )
        }
    }
}
