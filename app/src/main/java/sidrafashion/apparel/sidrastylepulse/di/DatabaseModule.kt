package sidrafashion.apparel.sidrastylepulse.di

import androidx.room.Room
import sidrafashion.apparel.sidrastylepulse.data.database.DDHKODatabase
import org.koin.dsl.module

private const val DB_NAME = "ddhko_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = DDHKODatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<DDHKODatabase>().cartItemDao() }

    single { get<DDHKODatabase>().orderDao() }
}