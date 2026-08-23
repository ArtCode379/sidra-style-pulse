package sidrafashion.apparel.sidrastylepulse.ui.composable.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sidrafashion.apparel.sidrastylepulse.ui.state.DataUiState

@Composable
fun <T> DDHKOContentWrapper(
    modifier: Modifier = Modifier,
    dataState: DataUiState<T>,
    dataPopulated: @Composable (() -> Unit),
    dataEmpty: @Composable (() -> Unit),
    dataInitial: @Composable (() -> Unit) = {},
) {
    Box(modifier = modifier) {
        when (dataState) {
            is DataUiState.Populated -> {
                dataPopulated()
            }

            DataUiState.Empty -> {
                dataEmpty()
            }

            DataUiState.Initial -> {
                dataInitial()
            }
        }
    }
}