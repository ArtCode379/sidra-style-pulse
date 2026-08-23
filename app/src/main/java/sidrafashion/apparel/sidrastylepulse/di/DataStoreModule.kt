package sidrafashion.apparel.sidrastylepulse.di

import sidrafashion.apparel.sidrastylepulse.data.datastore.DDHKOOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { DDHKOOnboardingPrefs(androidContext()) }
}