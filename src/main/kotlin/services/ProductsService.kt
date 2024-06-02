package services

import entities.product.Product
import repositories.products.ProductsRepository

class ProductsService {
    private val productsRepository: ProductsRepository
    
    constructor(productsRepository: ProductsRepository) {
       this.productsRepository = productsRepository
    }
    
    fun getAll(): List<Product> {
       return productsRepository.getAll() 
    }
    
    fun getByName(name: String): Product? {
       return productsRepository.getByName(name)
    }
}