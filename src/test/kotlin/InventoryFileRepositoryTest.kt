import entities.inventory.Inventory
import entities.inventory.InventoryItem
import mocks.MockFileManager
import repositories.inventory.InventoryFileRepository
import repositories.inventory.ItemUpdate
import kotlin.test.*

class InventoryFileRepositoryTest {
    
    @Test
    fun testGetAll() {
        val fileManager = MockFileManager(Inventory(mutableListOf(
            InventoryItem(1, "Leg", 2), 
            InventoryItem(2, "Plank", 3),
            InventoryItem(3, "Cushion", 1)
        ))) { }
        
        val repository = InventoryFileRepository(fileManager)
        
        val inventory = repository.getAll()
        
        assertEquals(3, inventory.size)
        assertEquals(2, (inventory.find { it.articleId == 1 } ?: throw Exception("Didn't find article 1")).stock)
        assertEquals(3, (inventory.find { it.articleId == 2 } ?: throw Exception("Didn't find article 2")).stock)
        assertEquals(1, (inventory.find { it.articleId == 3 } ?: throw Exception("Didn't find article 3")).stock)
    }
    
    
    
    @Test
    fun testUpdateMany() {
        val fileManager = MockFileManager(Inventory(mutableListOf(
            InventoryItem(1, "Leg", 2),
            InventoryItem(2, "Plank", 3),
            InventoryItem(3, "Cushion", 1)
        ))) {
            assertEquals(2, (it.inventoryItems.find { item -> item.articleId == 1 } ?: throw Exception("Didn't find article 1")).stock)
            assertEquals(58, (it.inventoryItems.find { item -> item.articleId == 2 } ?: throw Exception("Didn't find article 2")).stock)
            assertEquals(0, (it.inventoryItems.find { item -> item.articleId == 3 } ?: throw Exception("Didn't find article 3")).stock)
        }

        val repository = InventoryFileRepository(fileManager)
        
        assertTrue(repository.updateMany(listOf(ItemUpdate(2, 55), ItemUpdate(3, -1))))
    }
    
    @Test
    fun testUpdateIllegal() {
        val fileManager = MockFileManager(Inventory(mutableListOf(
            InventoryItem(1, "Leg", 2),
            InventoryItem(2, "Plank", 3),
            InventoryItem(3, "Cushion", 1)
        ))) {
            assertEquals(2, (it.inventoryItems.find { item -> item.articleId == 1 } ?: throw Exception("Didn't find article 1")).stock)
            assertEquals(3, (it.inventoryItems.find { item -> item.articleId == 2 } ?: throw Exception("Didn't find article 2")).stock)
            assertEquals(1, (it.inventoryItems.find { item -> item.articleId == 3 } ?: throw Exception("Didn't find article 3")).stock)
        } 
        
        val repository = InventoryFileRepository(fileManager)
        
        assertFalse(repository.updateMany(listOf(ItemUpdate(5, 3))))
    }
    
    @Test
    fun testNegativeUpdate() {
        val fileManager = MockFileManager(Inventory(mutableListOf(
            InventoryItem(1, "Leg", 2),
            InventoryItem(2, "Plank", 3),
            InventoryItem(3, "Cushion", 1)
        ))) {
            assertEquals(2, (it.inventoryItems.find { item -> item.articleId == 1 } ?: throw Exception("Didn't find article 1")).stock)
            assertEquals(3, (it.inventoryItems.find { item -> item.articleId == 2 } ?: throw Exception("Didn't find article 2")).stock)
            assertEquals(1, (it.inventoryItems.find { item -> item.articleId == 3 } ?: throw Exception("Didn't find article 3")).stock)
        }

        val repository = InventoryFileRepository(fileManager)

        assertFalse(repository.updateMany(listOf(ItemUpdate(3, -2))))
    }
}