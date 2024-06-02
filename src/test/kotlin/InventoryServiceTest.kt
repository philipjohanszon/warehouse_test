import entities.inventory.InventoryItem
import entities.product.Product
import entities.product.ProductArticle
import mocks.MockInventoryRepository
import org.junit.jupiter.api.Test
import services.InventoryService
import services.ProductsService
import kotlin.test.assertEquals

class InventoryServiceTest {
    
    @Test
    fun testGetProductStockCount() {
        val mockRepository = MockInventoryRepository(listOf(InventoryItem(1, "Leg", 6), InventoryItem(2, "Seat", 3))) {}
        
        val service = InventoryService(mockRepository)
        
        val chair1 = Product("Chair", listOf(ProductArticle(1, 4), ProductArticle(2, 1)))
        val chair2 = Product("Chair2", listOf(ProductArticle(1, 3), ProductArticle(2, 1)))
        val chair3 = Product("Chair3", listOf(ProductArticle(1, 10), ProductArticle(2, 1)))
        val chair4 = Product("Chair3", listOf(ProductArticle(1, 4), ProductArticle(2, 1), ProductArticle(4, 1)))
        
        assertEquals(1, service.getProductStockCount(chair1))
        assertEquals(2, service.getProductStockCount(chair2))
        assertEquals(0, service.getProductStockCount(chair3))
        assertEquals(0, service.getProductStockCount(chair4))
    }
    
    @Test
    fun testRemoveFromStock() {
        val mockRepository = MockInventoryRepository(listOf(InventoryItem(1, "Leg", 6), InventoryItem(2, "Seat", 3))) {
            assertEquals(2, it.size)     
            
            assertEquals(-5, it.sumOf { item -> item.stockChange })
        } 
        
        val service = InventoryService(mockRepository)

        val chair = Product("Chair", listOf(ProductArticle(1, 4), ProductArticle(2, 1)))
        
        assertEquals(true, service.removeProductFromStock(chair))
    }

    @Test
    fun testSingleArticleRemoveFromStock() {
        val mockRepository = MockInventoryRepository(listOf(InventoryItem(1, "Leg", 6), InventoryItem(2, "Seat", 3))) {
            assertEquals(1, it.size)
            assertEquals(1, it[0].articleId)
            assertEquals(-7, it[0].stockChange)
        }
        
        val service = InventoryService(mockRepository)

        val chair = Product("Chair", listOf(ProductArticle(1, 7)))

        assertEquals(true, service.removeProductFromStock(chair))
    }
}