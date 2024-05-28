package api

import data.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ProductsRepositoryApi {

    suspend fun getProductsApi() : List<Product>


    suspend fun getProductsApiFlow(): Flow<List<Product>>
}