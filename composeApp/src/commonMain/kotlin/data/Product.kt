package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("category")
    val category: Category,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String>,
    @SerialName("price")
    val price: Int,
    @SerialName("title")
    val title: String
)