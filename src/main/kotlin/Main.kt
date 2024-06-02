import Files.JSONFileManager
import entities.inventory.Inventory
import entities.product.Products
import repositories.inventory.InventoryFileRepository
import repositories.products.ProductsFileRepository
import services.InventoryService
import services.ProductsService

fun handleGetProducts(productsService: ProductsService, inventoryService: InventoryService) {
    val products = productsService.getAll()
    
    products.forEach {
        println("Product: ${it.name}")
        println("Stock: ${inventoryService.getProductStockCount(it)}")
    }
}

fun handleBuyProduct(productsService: ProductsService, inventoryService: InventoryService) {
    println("What product would you like to buy?")
    
    while (true) {
        print("Product name: ")
        val input = readln()
        
        val product = productsService.getByName(input) ?: continue
        
        if (!inventoryService.removeProductFromStock(product)) {
            println("Can't buy that, not enough in stock.")
            continue
        }
        
        break
    }
}

fun main() {
    val productsPath = "src/main/resources/products.json"
    val inventoryPath = "src/main/resources/inventory.json"
    
    val productsRepository = ProductsFileRepository(JSONFileManager(productsPath, Products.serializer()))
    val inventoryRepository = InventoryFileRepository(JSONFileManager(inventoryPath, Inventory.serializer()))
    
    val inventoryService = InventoryService(inventoryRepository)
    val productsService = ProductsService(productsRepository)
    
    while (true) {
        println("\n1. Get Products")
        println("2. Buy Product")
        print("Select choice [1, 2]: ")
        
        val input = readln()
        
        when (input) {
            "1" -> handleGetProducts(productsService, inventoryService)
            "2" -> handleBuyProduct(productsService, inventoryService)
            else -> println("\nBad choice, please input 1 or 2")
        }
    }
}
