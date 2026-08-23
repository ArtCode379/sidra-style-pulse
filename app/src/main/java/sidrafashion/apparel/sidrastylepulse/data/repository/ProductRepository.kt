package sidrafashion.apparel.sidrastylepulse.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import sidrafashion.apparel.sidrastylepulse.data.model.Product
import sidrafashion.apparel.sidrastylepulse.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Mulberry Satin Dress", "A fluid midi dress with a softly draped neckline, shaped waist and elegant movement for dinners and celebrations.",
            ProductCategory.DRESSES, 89.00, "https://images.unsplash.com/photo-1566174053879-31528523f8ae?w=1200",
        ),
        Product(
            2, "Ivory Pleated Dress", "Fine pleats and a clean silhouette create an effortless occasion piece that moves beautifully from day to evening.",
            ProductCategory.DRESSES, 96.00, "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=1200",
        ),
        Product(
            3, "Tailored Teal Blazer", "A confident single-breasted blazer with a defined shoulder, smooth lining and polished silver-tone buttons.",
            ProductCategory.OUTERWEAR, 112.00, "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?w=1200",
        ),
        Product(
            4, "Silk Bow Blouse", "A lightweight satin blouse with a softly tied neckline and relaxed cuffs, designed for refined everyday layering.",
            ProductCategory.TOPS, 54.00, "https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3?w=1200",
        ),
        Product(
            5, "Rose Evening Gown", "An occasion-ready floor-length gown with a sculpted bodice and graceful skirt in a romantic dusty rose tone.",
            ProductCategory.DRESSES, 149.00, "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?w=1200",
        ),
        Product(
            6, "Pearl Handle Bag", "A compact structured bag finished with pearl-effect handles and room for your evening essentials.",
            ProductCategory.ACCESSORIES, 47.00, "https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=1200",
        ),
        Product(
            7, "Cashmere Wrap Coat", "A timeless belted coat with a wide collar, warm hand feel and easy shape for elevated winter dressing.",
            ProductCategory.OUTERWEAR, 138.00, "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?w=1200",
        ),
        Product(
            8, "Gold Arc Earrings", "Sculptural gold-tone earrings with a light, comfortable finish that adds polish without overpowering a look.",
            ProductCategory.ACCESSORIES, 29.00, "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=1200",
        ),
        Product(
            9, "Linen Volume Shirt", "A breathable relaxed shirt with dropped shoulders and a curved hem, made for versatile tonal styling.",
            ProductCategory.TOPS, 49.00, "https://images.unsplash.com/photo-1605763240000-7e93b172d754?w=1200",
        ),
        Product(
            10, "Emerald Column Dress", "A sleek column dress with subtle gathering and a rich jewel tone, balancing modern simplicity with drama.",
            ProductCategory.DRESSES, 118.00, "https://images.unsplash.com/photo-1612336307429-8a898d10e223?w=1200",
        ),
        Product(
            11, "Leather Crescent Bag", "A softly curved shoulder bag with a smooth finish, secure zip and adjustable strap for daily wear.",
            ProductCategory.ACCESSORIES, 72.00, "https://images.unsplash.com/photo-1566150905458-1bf1fc113f0d?w=1200",
        ),
        Product(
            12, "Textured Knit Cardigan", "A soft cropped cardigan with dimensional stitching and elegant buttons for an easy layered silhouette.",
            ProductCategory.OUTERWEAR, 68.00, "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
