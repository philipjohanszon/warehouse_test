package entities.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product (
    @SerialName("name")
    val name: String,

    @SerialName("contain_articles")
    val productArticles: List<ProductArticle>,
)