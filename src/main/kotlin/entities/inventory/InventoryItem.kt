package entities.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import serializers.IntAsStringSerializer

@Serializable
data class InventoryItem (
    @SerialName("art_id")
    @Serializable(with = IntAsStringSerializer::class)
    val articleId: Int,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("stock")
    @Serializable(with = IntAsStringSerializer::class)
    var stock: Int
)