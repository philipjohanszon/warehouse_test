package entities.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import serializers.IntAsStringSerializer

@Serializable
data class ProductArticle (
    @SerialName("art_id")
    @Serializable(with = IntAsStringSerializer::class)
    val articleId: Int,
    
    @SerialName("amount_of")
    @Serializable(with = IntAsStringSerializer::class)
    val amountNeeded: Int
)