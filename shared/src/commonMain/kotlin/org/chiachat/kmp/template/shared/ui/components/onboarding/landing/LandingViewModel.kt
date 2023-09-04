package org.chiachat.kmp.template.shared.ui.components.onboarding.landing

import org.chiachat.kmp.template.shared.ui.components.KoinViewModel
import org.chiachat.kmp.template.shared.ui.components.ViewModel
import org.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginComponent
import org.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationComponent
import org.chiachat.kmp.template.shared.ui.services.NavigationService
import org.koin.core.component.inject

internal interface LandingViewModel : ViewModel {
    val navigationService: NavigationService

    fun onLogin()

    fun onRegister()
}

internal class LandingViewModelImpl : KoinViewModel(), LandingViewModel {
    override val navigationService: NavigationService by inject()

    override fun onLogin() {
        navigationService.navigate(LoginComponent())
    }

    override fun onRegister() {
        navigationService.navigate(RegistrationComponent())
    }
}
