package sidrafashion.apparel.sidrastylepulse.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import sidrafashion.apparel.sidrastylepulse.R
import sidrafashion.apparel.sidrastylepulse.ui.composable.shared.DDHKOContentWrapper
import sidrafashion.apparel.sidrastylepulse.ui.composable.shared.DDHKOEmptyView
import sidrafashion.apparel.sidrastylepulse.ui.state.CartItemUiState
import sidrafashion.apparel.sidrastylepulse.ui.state.DataUiState
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()
    DDHKOContentWrapper(
        dataState = cartItemsState,
        dataPopulated = {
            val items = (cartItemsState as DataUiState.Populated).data
            CartContent(
                items = items,
                total = totalPrice,
                modifier = modifier,
                onIncrease = viewModel::incrementProductInCart,
                onDecrease = { item ->
                    if (item.quantity == 1) {
                        viewModel.deleteFromCart(item.productId)
                    } else {
                        viewModel.decrementItemInCart(item.productId)
                    }
                },
                onRemove = viewModel::deleteFromCart,
                onCheckout = onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                DDHKOEmptyView(
                    primaryText = stringResource(R.string.ddhko_cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = stringResource(R.string.ddhko_start_shopping),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
    )
}

@Composable
private fun CartContent(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    onIncrease: (Int) -> Unit,
    onDecrease: (CartItemUiState) -> Unit,
    onRemove: (Int) -> Unit,
    onCheckout: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(stringResource(R.string.ddhko_bag_title), style = MaterialTheme.typography.headlineMedium)
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items, key = { it.productId }) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(1.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = item.productImageUrl,
                            contentDescription = item.productTitle,
                            modifier = Modifier.size(70.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp),
                        ) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text(
                                stringResource(R.string.ddhko_price, item.productPrice),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(onClick = { onDecrease(item) }, contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)) {
                                    Text("−")
                                }
                                Text(
                                    text = item.quantity.toString(),
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                )
                                OutlinedButton(onClick = { onIncrease(item.productId) }, contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)) {
                                    Text("+")
                                }
                            }
                        }
                        IconButton(onClick = { onRemove(item.productId) }) {
                            Icon(Icons.Outlined.Delete, stringResource(R.string.ddhko_delete_item_icon_description))
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(stringResource(R.string.ddhko_total), style = MaterialTheme.typography.titleLarge)
            Text(
                stringResource(R.string.ddhko_price, total),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(stringResource(R.string.ddhko_proceed_checkout))
        }
    }
}
