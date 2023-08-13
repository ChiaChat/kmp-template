package org.chiachat.kmp.template.shared.ui.components.onboarding.register

import androidx.compose.runtime.Composable
import org.chiachat.kmp.template.shared.ui.components.Component

internal class RegistrationComponent : Component {
    override val vm: RegistrationViewModel = RegistrationViewModelImpl()

    @Composable
    override fun View() {
        RegistrationComposables.RegistrationScreen(vm::onCreate)
    }
}
