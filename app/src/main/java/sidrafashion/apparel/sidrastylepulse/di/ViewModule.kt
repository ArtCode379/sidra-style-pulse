package sidrafashion.apparel.sidrastylepulse.di

import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.AppViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.CartViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.CheckoutViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.DDHKOOnboardingVM
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.OrderViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.ProductDetailsViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.ProductViewModel
import sidrafashion.apparel.sidrastylepulse.ui.viewmodel.DDHKOSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        DDHKOSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        DDHKOOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}