package repositories.products

import entities.product.Product

interface ProductsRepository {
    fun getAll(): List<Product>
    fun getByName(name: String): Product?
}