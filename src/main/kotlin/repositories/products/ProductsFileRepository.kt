package repositories.products

import Files.FileManager
import entities.product.Product
import entities.product.Products

class ProductsFileRepository : ProductsRepository {
    private val fileManager: FileManager<Products>
    
    constructor(fileManager: FileManager<Products>) {
        this.fileManager = fileManager
    }

    override fun getAll(): List<Product> {
        return fileManager.get().products
    }

    override fun getByName(name: String): Product? {
        return fileManager.get().products.find { it.name == name }
    }
}