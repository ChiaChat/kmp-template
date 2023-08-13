package org.chiachat.kmp.template.shared.ui.components.onboarding.login

import co.touchlab.kermit.Logger
import org.chiachat.kmp.template.shared.ui.components.KoinViewModel
import org.chiachat.kmp.template.shared.ui.components.ViewModel
import org.chiachat.kmp.template.shared.ui.services.ResourceService
import org.chiachat.kmp.template.shared.ui.services.ThemeService
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
