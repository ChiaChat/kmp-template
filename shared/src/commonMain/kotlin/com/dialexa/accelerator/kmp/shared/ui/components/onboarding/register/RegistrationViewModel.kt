package com.dialexa.accelerator.kmp.shared.ui.components.onboarding.register

import com.dialexa.accelerator.kmp.shared.ui.components.KoinViewModel
import com.dialexa.accelerator.kmp.shared.ui.components.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

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
