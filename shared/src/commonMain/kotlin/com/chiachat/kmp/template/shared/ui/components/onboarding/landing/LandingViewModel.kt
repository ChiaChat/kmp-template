package com.chiachat.kmp.template.shared.ui.components.onboarding.landing

import com.chiachat.kmp.template.shared.ui.components.KoinViewModel
import com.chiachat.kmp.template.shared.ui.components.ViewModel
import com.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginComponent
import com.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationComponent
import com.chiachat.kmp.template.shared.ui.services.NavigationService
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
