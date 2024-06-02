package mocks

import entities.product.Product
import repositories.products.ProductsRepository

class MockProductsRepository : ProductsRepository {
    var data: List<Product>
    
    constructor(data: List<Product>) {
        this.data = data
    }
    
    override fun getAll(): List<Product> {
        return data
    }

    override fun getByName(name: String): Product? {
        return data.find { it.name == name }
    }
}