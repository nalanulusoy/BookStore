import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bookstore.component.BookImageView
import com.app.bookstore.db.BookData

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeCardItem(dismissState: DismissState,item:BookData){
    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp).testTag(item.id),
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = {
            FractionalThreshold( 0.5f)
        },
        background = {
            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                elevation = animateDpAsState(
                    if (dismissState.dismissDirection != null) 4.dp else 0.dp
                ).value
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Red),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Localized description",
                        modifier = Modifier.scale(scale)
                    )
                }
            }
        },
        dismissContent = {
            CardItemRow(
                bookData = item
            )
        }
    )
}

@Composable
fun CardItemRow(
    modifier: Modifier = Modifier,
    bookData: BookData
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight()   ,
        backgroundColor = Color.White
    ) {
        Row(modifier = modifier) {
            BookImageView(imageUrl = bookData.imageUrl.orEmpty(), modifier = Modifier.weight(6f))
            Text(
                text = bookData.title.orEmpty(), modifier = Modifier
                    .weight(10f)
                    .padding(end = 4.dp, top = 10.dp),color = Color.Black
            )
            Text(
                text = bookData.publishData.orEmpty(), modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp, bottom = 10.dp),color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun SwipeCardItemPreview() {
    val dismissState = rememberDismissState()
    val bookData = BookData("2022","dummy","dummy","1234")
    SwipeCardItem(dismissState = dismissState, item = bookData )
}
