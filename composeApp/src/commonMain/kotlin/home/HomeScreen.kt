package home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import components.ProductSection
import data.Product

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel =  getScreenModel<HomeViewModel>()
        HomeContent( viewModel = viewModel)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    var textState by remember { mutableStateOf("hint") }
    val isHint = { textState == "" }
    val products = viewModel.productsList

    BackdropScaffold(
        peekHeight = 100.dp,
        modifier = modifier.background(color = Color.Gray),
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        appBar = {

        },
        backLayerContent = {
            SearchTextBox("Search" , ){
                viewModel.onSearchProduct(it)
            }
            Spacer(Modifier.height(60.dp))
        },
        frontLayerContent = {
            ProductSection(
                title = "Search for Products",
                products = products,
                onItemClicked = {}
            )
        }
    )
}


@Composable
fun SearchTextBox(
    hint :String = "",
    text : String = "",
    onValueChanged : (String) -> Unit,
){
    var initialText  by remember { mutableStateOf(text) }

    var isFocused  by remember {
        mutableStateOf(hint != "")
    }

    Box(
        modifier =Modifier.padding(16.dp),
        contentAlignment = Alignment.CenterStart){
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(40.dp)
                )
                .background(Color.White)
                .padding(16.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            value = initialText,
            onValueChange = {
                initialText = it
                onValueChanged(it)
            },
        )
        if(!isFocused){
            Text(text = hint , color = Color.LightGray , modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}