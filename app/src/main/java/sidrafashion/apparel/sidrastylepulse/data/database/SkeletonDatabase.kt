package sidrafashion.apparel.sidrastylepulse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sidrafashion.apparel.sidrastylepulse.data.dao.CartItemDao
import sidrafashion.apparel.sidrastylepulse.data.dao.OrderDao
import sidrafashion.apparel.sidrastylepulse.data.database.converter.Converters
import sidrafashion.apparel.sidrastylepulse.data.entity.CartItemEntity
import sidrafashion.apparel.sidrastylepulse.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DDHKODatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}