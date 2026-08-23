package sidrafashion.apparel.sidrastylepulse.data.repository

import sidrafashion.apparel.sidrastylepulse.data.datastore.DDHKOOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DDHKOOnboardingRepo(
    private val ddhkoOnboardingStoreManager: DDHKOOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ddhkoOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ddhkoOnboardingStoreManager.setOnboardedState(state)
        }
    }
}