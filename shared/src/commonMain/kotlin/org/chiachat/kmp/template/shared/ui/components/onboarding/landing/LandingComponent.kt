package org.chiachat.kmp.template.shared.ui.components.onboarding.landing

import androidx.compose.runtime.Composable
import org.chiachat.kmp.template.shared.ui.components.Component

internal class LandingComponent : Component {
    override val vm: LandingViewModel = LandingViewModelImpl()

    @Composable
    override fun View() {
        LandingComposables.LandingScreen { login ->
            if (login) {
                vm.onLogin()
            } else {
                vm.onRegister()
            }
        }
    }
}
