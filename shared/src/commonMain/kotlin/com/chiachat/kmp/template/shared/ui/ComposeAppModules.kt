package com.chiachat.kmp.template.shared.ui

import com.chiachat.kmp.template.shared.ui.components.onboarding.landing.LandingComponent
import com.chiachat.kmp.template.shared.ui.components.onboarding.landing.LandingViewModelImpl
import com.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginComponent
import com.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginViewModelImpl
import com.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationComponent
import com.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationViewModelImpl
import com.chiachat.kmp.template.shared.ui.services.NavigationService
import com.chiachat.kmp.template.shared.ui.services.ResourceService
import com.chiachat.kmp.template.shared.ui.services.ThemeService
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ComposeAppModules {
    val components = module {
        singleOf(::LandingComponent)
        singleOf(::LandingViewModelImpl)
        singleOf(::LoginComponent)
        singleOf(::LoginViewModelImpl)
        singleOf(::RegistrationComponent)
        singleOf(::RegistrationViewModelImpl)
    }

    val services = module {
        single { NavigationService(get<LandingComponent>()) }
        single { ResourceService(get(named("ioScope"))) }
        singleOf(::ThemeService)
    }

    val all = services + components
}
