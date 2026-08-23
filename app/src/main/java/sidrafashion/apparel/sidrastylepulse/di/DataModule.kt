package sidrafashion.apparel.sidrastylepulse.di

import sidrafashion.apparel.sidrastylepulse.data.repository.CartRepository
import sidrafashion.apparel.sidrastylepulse.data.repository.DDHKOOnboardingRepo
import sidrafashion.apparel.sidrastylepulse.data.repository.OrderRepository
import sidrafashion.apparel.sidrastylepulse.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        DDHKOOnboardingRepo(
            ddhkoOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}