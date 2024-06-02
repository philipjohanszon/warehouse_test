package entities.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Products {
    @SerialName("products")
    val products: MutableList<Product>
    
    constructor(products: MutableList<Product>) {
        this.products = products
    }

    override fun toString(): String {
        return products.joinToString(", ") { product: Product -> product.toString() }
    }
}