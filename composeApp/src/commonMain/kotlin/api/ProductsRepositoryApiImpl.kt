package api

import data.Product
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepositoryApiImpl : ProductsRepositoryApi {
    override suspend fun getProductsApi(): List<Product> {
        val response =   httpClient.get("https://api.escuelajs.co/api/v1/products")

        print(response?.body())
        print(response?.body())
        print(response?.body())
        return response.body()
    }

    override suspend fun getProductsApiFlow(): Flow<List<Product>> = flow {
        val response  = httpClient.get("https://api.escuelajs.co/api/v1/products")

        print(response?.body())
        print(response?.body())
        print(response?.body())
        emit(response.body())
    }
}