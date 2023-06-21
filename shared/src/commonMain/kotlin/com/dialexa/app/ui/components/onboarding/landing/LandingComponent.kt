package com.dialexa.app.ui.components.onboarding.landing

import androidx.compose.runtime.Composable
import com.dialexa.app.ui.components.Component

internal class LandingComponent : Component {
    override val vm: LandingViewModel = LandingViewModelImpl()

    @Composable
    override fun View() {
        LandingScreen { login ->
            if (login) {
                vm.onLogin()
            } else {
                vm.onRegister()
            }
        }
    }
}
