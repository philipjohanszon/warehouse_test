package mocks

import entities.inventory.InventoryItem
import repositories.inventory.InventoryRepository
import repositories.inventory.ItemUpdate

class MockInventoryRepository : InventoryRepository {
    var data: List<InventoryItem>
    var onMultiUpdate: (List<ItemUpdate>) -> Unit
    
    constructor(data: List<InventoryItem>, onMultiUpdate: (List<ItemUpdate>) -> Unit) {
       this.data = data 
       this.onMultiUpdate = onMultiUpdate
    }
    
    override fun getAll(): List<InventoryItem> {
        return data
    }

    override fun updateMany(updates: List<ItemUpdate>): Boolean {
        onMultiUpdate(updates)
        return true
    }

}