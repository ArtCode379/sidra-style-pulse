package sidrafashion.apparel.sidrastylepulse.data.model

import androidx.annotation.StringRes
import sidrafashion.apparel.sidrastylepulse.R

enum class ProductCategory(
    @field:StringRes val titleRes: Int,
) {
    DRESSES(R.string.ddhko_category_dresses),
    TOPS(R.string.ddhko_category_tops),
    OUTERWEAR(R.string.ddhko_category_outerwear),
    ACCESSORIES(R.string.ddhko_category_accessories),
}
