package sidrafashion.apparel.sidrastylepulse.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import sidrafashion.apparel.sidrastylepulse.R
import sidrafashion.apparel.sidrastylepulse.data.entity.OrderEntity
import sidrafashion.apparel.sidrastylepulse.ui.composable.shared.DDHKOContentWrapper
import sidrafashion.apparel.sidrastylepulse.ui.composable.shared.DDHKOEmptyView
import sidrafashion.apparel.sidrastylepulse.ui.state.DataUiState
import sidrafashion.apparel.sidrastylepulse.ui.theme.Success
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()
    DDHKOContentWrapper(
        dataState = ordersState,
        dataPopulated = {
            val orders = (ordersState as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    Text(stringResource(R.string.ddhko_orders_heading), style = MaterialTheme.typography.headlineMedium)
                }
                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(order)
                }
            }
        },
        dataEmpty = {
            DDHKOEmptyView(
                primaryText = stringResource(R.string.ddhko_orders_state_empty_primary_text),
                modifier = modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.ddhko_order_number, order.orderNumber),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(R.string.ddhko_order_reserved),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .then(Modifier),
                    color = Success,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            Text(
                text = order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = order.description,
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.ddhko_price, order.price),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = stringResource(R.string.ddhko_collection_notice),
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}
