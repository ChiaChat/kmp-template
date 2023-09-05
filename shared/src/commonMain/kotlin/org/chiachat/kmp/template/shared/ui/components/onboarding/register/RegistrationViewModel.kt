package org.chiachat.kmp.template.shared.ui.components.onboarding.register

import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.kmp.template.shared.ui.components.KoinViewModel
import org.chiachat.kmp.template.shared.ui.components.ViewModel

internal interface RegistrationViewModel : ViewModel {
    val username: MutableStateFlow<String>
    val password: MutableStateFlow<String>
    val email: MutableStateFlow<String>

    fun onCreate()
}

internal class RegistrationViewModelImpl : KoinViewModel(), RegistrationViewModel {
    override val username: MutableStateFlow<String> = MutableStateFlow("")
    override val password: MutableStateFlow<String> = MutableStateFlow("")
    override val email: MutableStateFlow<String> = MutableStateFlow("")

    override fun onCreate() {
        TODO("Not yet implemented")
    }
}
