package org.chiachat.kmp.template.shared.ui.components.onboarding.login

import androidx.compose.runtime.Composable
import org.chiachat.kmp.template.shared.ui.components.Component
import org.chiachat.kmp.template.shared.ui.services.NavigationService
import org.chiachat.kmp.template.shared.ui.services.ResourceService
import org.koin.core.component.inject

internal class LoginComponent : Component {

    override val vm: LoginViewModel = LoginViewModelImpl()

    val resources: ResourceService by inject()
    val navigationService: NavigationService by inject()

    @Composable
    override fun View() {
        LoginComposables.LoginScreen({}, { navigationService.back() })
    }
}
