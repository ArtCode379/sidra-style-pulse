package sidrafashion.apparel.sidrastylepulse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BrandColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Accent,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onBackground = OnSurface,
    outline = Border,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = Muted,
)

@Composable
fun ProductAppDDHKOTheme(
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = BrandColorScheme,
        typography = AppTypography,
        content = content,
    )
}
