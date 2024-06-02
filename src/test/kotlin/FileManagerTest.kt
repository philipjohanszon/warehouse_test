import Files.JSONFileManager
import entities.inventory.Inventory
import entities.inventory.InventoryItem
import entities.product.Product
import entities.product.ProductArticle
import entities.product.Products
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class FileManagerTest {
    @Test
    fun getDataTest() {
        val productsFileManager = JSONFileManager("src/test/resources/products.json", Products.serializer())
        val inventoryFileManager = JSONFileManager("src/test/resources/inventory.json", Inventory.serializer())
        
        val products = productsFileManager.get()
        val inventory = inventoryFileManager.get()
        
        assertEquals(2, products.products.size)
        assertEquals(4, inventory.inventoryItems.size)
    }   
    
    @Test
    fun saveDataTest() {
        val testProductsPath = "src/test/resources/testProducts.json"
        val testInventoryPath = "src/test/resources/testInventory.json"
        
        File(testProductsPath).writeText("{\"products\": []}")
        File(testInventoryPath).writeText("{\"inventory\": []}")

        val productsFileManager = JSONFileManager(testProductsPath, Products.serializer())
        val inventoryFileManager = JSONFileManager(testInventoryPath, Inventory.serializer())
        
        val products = productsFileManager.get()
        val inventory = inventoryFileManager.get()
        
        products.products += Product("Chair", listOf(ProductArticle(1, 2)))
        inventory.inventoryItems += InventoryItem(1, "Leg", 3)
        
        productsFileManager.save(products)
        inventoryFileManager.save(inventory)

        val newProductsFileManager = JSONFileManager(testProductsPath, Products.serializer())
        val newInventoryFileManager = JSONFileManager(testInventoryPath, Inventory.serializer())
        
        assertEquals(1, newProductsFileManager.get().products.size)
        assertEquals(1, newInventoryFileManager.get().inventoryItems.size)
        
        File(testProductsPath).delete()
        File(testInventoryPath).delete()
    }
}
