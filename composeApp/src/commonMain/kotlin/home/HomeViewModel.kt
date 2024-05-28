package home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import api.ProductsRepositoryApi
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val productsRepositoryApi: ProductsRepositoryApi) : ScreenModel {

    private  val _products  = MutableStateFlow<List<Product>>(listOf())
    val products  =  _products.asStateFlow()

    private var _productsList by mutableStateOf<List<Product>>(emptyList())
    val productsList: List<Product> get() = _productsList




    init {
//        screenModelScope.launch(Dispatchers.Main) {
//            try {
//                productsRepositoryApi.getProductsApiFlow().collect{ items ->
//                    _products.update {
//                        it + items }
//                }
//            }catch (e : Exception){
//                println(e.message)
//            }
//        }

        screenModelScope.launch(Dispatchers.Main) {
            try {
                _productsList  = productsRepositoryApi.getProductsApi()
            }catch (e : Exception){
                println(e.message)
            }
        }
    }


    fun onSearchProduct(newDestination: String) {
        screenModelScope.launch {
             _productsList = withContext(Dispatchers.Main) {
                productsRepositoryApi.getProductsApi()
                    .filter { it.title.contains(newDestination) }
            }
        }
    }
}



