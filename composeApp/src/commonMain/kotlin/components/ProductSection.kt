package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import data.Product
import viewmore.ViewMoreScreen


@Composable
fun ProductSection(
    modifier: Modifier = Modifier,
    title: String,
    products: List<Product>,
    onItemClicked: (Product) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, ) {
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))

            val listState = rememberLazyListState()
            ProductList(products, onItemClicked, listState = listState)
        }
    }
}

@Composable
private fun ProductList(
    products: List<Product>,
    onItemClicked: (Product) -> Unit ,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
        state = listState
    ) {
        items(products) { exploreItem ->
            Column(Modifier.fillParentMaxWidth()) {
                ProductItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = exploreItem,
                )
                Divider(color = Color.Gray)
            }
        }
    }
}

@Composable
private fun ProductItem(
    modifier: Modifier = Modifier,
    item: Product,
) {

    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = modifier
            .clickable {
                navigator.push(ViewMoreScreen(item))
            }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        ExploreImageContainer {
            ExploreImageContainer {
                Box {
                    val imageUrl = item.images[0]
                    val painter = rememberImagePainter(imageUrl)

                    Image(painter , modifier = Modifier.height(130.dp).shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(20.dp)) , contentDescription = item.title)

                }
            }
        }
        Spacer(Modifier.width(24.dp))
        Column {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = item.category.name,
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
                Text(
                    text = "|",
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
                Text(
                    text =  "R ${item.price.toString()}",
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
            }

        }
    }
}

@Composable
private fun ExploreImageContainer(content: @Composable () -> Unit) {
    Surface(Modifier.size(width = 60.dp, height = 60.dp), RoundedCornerShape(4.dp)) {
        content()
    }
}