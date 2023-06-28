package com.dialexa.accelerator.kmp.shared.ui.components.onboarding.login

import co.touchlab.kermit.Logger
import com.dialexa.accelerator.kmp.shared.ui.components.KoinViewModel
import com.dialexa.accelerator.kmp.shared.ui.components.ViewModel
import com.dialexa.accelerator.kmp.shared.ui.services.ResourceService
import com.dialexa.accelerator.kmp.shared.ui.services.ThemeService
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.inject

internal interface LoginViewModel : ViewModel {
    val themeService: ThemeService
    val resourceService: ResourceService

    val username: MutableStateFlow<String>
    val password: MutableStateFlow<String>

    fun onLogin()
}

internal class LoginViewModelImpl : KoinViewModel(), LoginViewModel {
    override val themeService: ThemeService by inject()
    override val resourceService: ResourceService by inject()

    override val username: MutableStateFlow<String> = MutableStateFlow("")
    override val password: MutableStateFlow<String> = MutableStateFlow("")

    override fun onLogin() {
        Logger.i { "Login tapped" }
    }
}
