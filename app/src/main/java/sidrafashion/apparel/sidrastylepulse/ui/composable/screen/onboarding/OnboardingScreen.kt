package sidrafashion.apparel.sidrastylepulse.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import sidrafashion.apparel.sidrastylepulse.R
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.DDHKOOnboardingVM

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int,
)

private val onboardingPagesContent = listOf(
    OnboardingContent(R.string.ddhko_page_1_title, R.string.ddhko_page_1_description, R.drawable.onboarding_1),
    OnboardingContent(R.string.ddhko_page_2_title, R.string.ddhko_page_2_description, R.drawable.onboarding_2),
    OnboardingContent(R.string.ddhko_page_3_title, R.string.ddhko_page_3_description, R.drawable.onboarding_3),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: DDHKOOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { onboardingPagesContent.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { page ->
            val content = onboardingPagesContent[page]
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(content.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(520.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f))
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                ) {
                    Text(
                        text = stringResource(content.titleRes),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = stringResource(content.descriptionRes),
                        modifier = Modifier.padding(top = 10.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                onboardingPagesContent.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == pagerState.currentPage) 10.dp else 7.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.outline
                                }
                            ),
                    )
                }
            }
            Button(
                onClick = {
                    if (pagerState.currentPage == onboardingPagesContent.lastIndex) {
                        viewModel.setOnboarded()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
            ) {
                Text(
                    text = stringResource(
                        if (pagerState.currentPage == onboardingPagesContent.lastIndex) {
                            R.string.ddhko_start_button_title
                        } else {
                            R.string.ddhko_next_button_title
                        }
                    )
                )
            }
        }
    }
}
