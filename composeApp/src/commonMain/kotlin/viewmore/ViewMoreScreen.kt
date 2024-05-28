package viewmore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import data.Product

class ViewMoreScreen(private val product: Product)  : Screen{

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = { Text(text = "Like this Product ?") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.pop()
                        }){
                            Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "null")
                        }
                    }
                ) },
        ) { padding  ->
            Column(Modifier.fillMaxSize().padding(10.dp)) {
                Spacer(Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(25.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyRow {
                        items(product.images.size) { index ->
                            val image = product.images[index]
                            val painter = rememberImagePainter(image)
                            Box(
                                modifier = Modifier
                                    .size(150.dp, 130.dp)
                            ) {
                                Image(
                                    painter = painter,
                                    contentDescription = product.title,
                                    modifier = Modifier.size(130.dp, 130.dp).clip(shape = RoundedCornerShape(100.dp)) // Adjusting size again within Image
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(25.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Category: ${product.category.name}",
                        )
                        Spacer(Modifier.height(8.dp))

                        Text(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Description :",
                        )

                        Text(
                            text = "${product.description}",
                        )
                        Spacer(Modifier.height(30.dp))

                        Text(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Price:   R ${product.price}",
                        )
                    }
                }
            }
        }

    }
}