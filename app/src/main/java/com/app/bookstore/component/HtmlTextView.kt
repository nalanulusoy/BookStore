package com.app.bookstore.component

import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 08,Temmuz,2022
 */
@Composable
fun HtmlTextView(
    description: String,
    textColor: Int = R.color.gray,
    textSize: Int = R.dimen.description_size,
    htmlCompat: Int = HtmlCompat.FROM_HTML_MODE_LEGACY,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AppCompatTextView(context).apply {
                setTextColor(context.resources.getColor(textColor))
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(textSize)
                )
            }
        },
        update = { it.text = HtmlCompat.fromHtml(description,htmlCompat) }
    )
}

@Preview("HtmlTextView")
@Composable
fun HtmlTextViewPreview() {
    MaterialTheme {
        Surface {
            HtmlTextView(
                description = "<br>deneme deneme deneme</br>",
                textSize = R.dimen.search_text_size
            )
        }
    }
}