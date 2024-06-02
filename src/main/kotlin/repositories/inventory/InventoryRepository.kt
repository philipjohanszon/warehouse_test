package repositories.inventory

import entities.inventory.InventoryItem

data class ItemUpdate(val articleId: Int, val stockChange: Int)

interface InventoryRepository {
    fun getAll(): List<InventoryItem>
    fun updateMany(updates: List<ItemUpdate>): Boolean
}