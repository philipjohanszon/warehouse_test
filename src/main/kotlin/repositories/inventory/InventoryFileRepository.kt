package repositories.inventory

import Files.FileManager
import entities.inventory.Inventory
import entities.inventory.InventoryItem
import entities.product.Product
import entities.product.Products

class InventoryFileRepository : InventoryRepository {
    private val fileManager: FileManager<Inventory>
    private var inventory: Inventory 
    
    constructor(fileManager: FileManager<Inventory>) {
        this.fileManager = fileManager
        this.inventory = fileManager.get()
    }

    override fun getAll(): List<InventoryItem> {
        return inventory.inventoryItems.map { it.copy() }
    }
    
    override fun updateMany(updates: List<ItemUpdate>): Boolean {
        val copyOfItems = inventory.inventoryItems.map { it.copy() }
        
        updates.forEach {
            if (!updateItem(it, copyOfItems)) return false
        }
        
        inventory = Inventory(copyOfItems.toMutableList())
        
        fileManager.save(inventory)
        
        return true
    }
    
    private fun updateItem(update: ItemUpdate, items: List<InventoryItem>): Boolean {
        val item = items.find { it.articleId == update.articleId } ?: return false

        if (item.stock + update.stockChange >= 0) {
            item.stock += update.stockChange
        } else {
            return false
        }
        
        return true
    }
}
