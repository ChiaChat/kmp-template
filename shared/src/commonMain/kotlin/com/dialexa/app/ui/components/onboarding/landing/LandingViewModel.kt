package com.dialexa.app.ui.components.onboarding.landing

import kotlinx.coroutines.flow.MutableStateFlow
import com.dialexa.app.ui.components.KoinViewModel
import com.dialexa.app.ui.components.ViewModel
import com.dialexa.app.ui.components.onboarding.login.LoginComponent
import com.dialexa.app.ui.components.onboarding.register.RegistrationComponent
import com.dialexa.app.ui.services.NavigationService
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
