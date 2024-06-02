import entities.inventory.InventoryItem
import entities.product.Product
import entities.product.ProductArticle
import entities.product.Products
import mocks.MockFileManager
import repositories.products.ProductsFileRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductsFileRepositoryTest {
    
    @Test
    fun getAllTest() {
        val fileManager = MockFileManager(
            Products(mutableListOf(
                Product("Chair", mutableListOf(ProductArticle(2, 3))), 
                Product("Table", mutableListOf(ProductArticle(1, 4), ProductArticle(2, 3)))
            ))
        ) { } 
        
        val repository = ProductsFileRepository(fileManager)
        
        assertEquals(2, repository.getAll().size)
    }
    
    @Test
    fun getByNameTest() {
        val fileManager = MockFileManager(
            Products(mutableListOf(
                Product("Chair", mutableListOf(ProductArticle(2, 3))),
                Product("Table", mutableListOf(ProductArticle(1, 4), ProductArticle(2, 3)))
            ))
        ) { }
        
        val repository = ProductsFileRepository(fileManager)
        
        assertEquals(2, (repository.getByName("Table") ?: throw Exception("Couldn't find Table")).productArticles.size)
        assertEquals(null, repository.getByName("table"))
        assertEquals(null, repository.getByName("couch"))
    }
}