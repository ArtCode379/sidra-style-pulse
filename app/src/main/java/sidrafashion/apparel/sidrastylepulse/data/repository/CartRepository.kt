package sidrafashion.apparel.sidrastylepulse.data.repository

import sidrafashion.apparel.sidrastylepulse.data.dao.CartItemDao
import sidrafashion.apparel.sidrastylepulse.data.entity.CartItemEntity
import sidrafashion.apparel.sidrastylepulse.data.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CartRepository(
    private val cartItemDao: CartItemDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeAll(): Flow<List<CartItemEntity>> {
        return cartItemDao.observeAll()
    }

    suspend fun getAll(): List<CartItemEntity> {
        return cartItemDao.getAll()
    }

    suspend fun deleteById(id: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteById(id)
        }
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteAll()
        }
    }

    suspend fun incrementQuantity(productId: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementQuantity(productId)
        }
    }

    suspend fun incrementProductQuantityOrAdd(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementProductQuantityOrAdd(product)
        }
    }

    suspend fun decrementProductQuantityOrRemove(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.decrementProductQuantityOrRemove(product)
        }
    }
}