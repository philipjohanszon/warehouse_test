import entities.product.Product
import entities.product.ProductArticle
import mocks.MockProductsRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProductsServiceTest {
    @Test
    fun testGetAll() {
        val repository = MockProductsRepository(listOf(
            Product("Table", listOf(ProductArticle(1, 4), ProductArticle(2, 1))), 
            Product("Chair", listOf(ProductArticle(1, 4), ProductArticle(3, 1))),
            Product("Couch", listOf(ProductArticle(1, 6), ProductArticle(3, 3)))
        )) 
        
        assertEquals(3, repository.getAll().size)
    }

    @Test
    fun testGetByName() {
        val repository = MockProductsRepository(listOf(
            Product("Table", listOf(ProductArticle(1, 4), ProductArticle(2, 1))),
            Product("Chair", listOf(ProductArticle(1, 4), ProductArticle(3, 1))),
            Product("Couch", listOf(ProductArticle(1, 6), ProductArticle(3, 3)))
        ))

        assertEquals(2, repository.getByName("Table")?.productArticles?.size)
        assertEquals(null, repository.getByName("chair"))
    }
}