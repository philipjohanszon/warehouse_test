package entities.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Inventory {
    @SerialName("inventory")
    val inventoryItems: MutableList<InventoryItem>
    
    constructor(inventoryItems: MutableList<InventoryItem>) {
        this.inventoryItems = inventoryItems;
    }

    override fun toString(): String {
        return inventoryItems.joinToString(", ") { inventoryItems -> inventoryItems.toString() }
    }
}