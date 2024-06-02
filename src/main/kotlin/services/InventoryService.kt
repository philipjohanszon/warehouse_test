package services

import entities.product.Product
import repositories.inventory.InventoryRepository
import repositories.inventory.ItemUpdate

class InventoryService {
    private val inventoryRepository: InventoryRepository
    
    constructor(inventoryRepository: InventoryRepository) {
       this.inventoryRepository = inventoryRepository
    }
    
    fun getProductStockCount (product: Product): Int {
        val inventoryItems = inventoryRepository.getAll()
        var lowestAmount = Int.MAX_VALUE
        val articlesNeeded = mutableMapOf<Int, Int>()

        product.productArticles.forEach {
            articlesNeeded[it.articleId] = it.amountNeeded
        }

        if (articlesNeeded.isEmpty()) return 0

        articlesNeeded.forEach {
            val stockCount = (inventoryItems.find { item -> item.articleId == it.key } ?: return 0).stock

            val available = stockCount / it.value

            if (available < lowestAmount) lowestAmount = available
        }

        return lowestAmount
    }
    
    fun removeProductFromStock(product: Product): Boolean {
        val updates = product.productArticles.map {
            ItemUpdate(it.articleId, it.amountNeeded * -1)
        }
        
        return inventoryRepository.updateMany(updates)
    }
}