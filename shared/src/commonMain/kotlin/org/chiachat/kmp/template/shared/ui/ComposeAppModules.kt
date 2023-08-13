package org.chiachat.kmp.template.shared.ui

import org.chiachat.kmp.template.shared.ui.components.onboarding.landing.LandingComponent
import org.chiachat.kmp.template.shared.ui.components.onboarding.landing.LandingViewModelImpl
import org.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginComponent
import org.chiachat.kmp.template.shared.ui.components.onboarding.login.LoginViewModelImpl
import org.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationComponent
import org.chiachat.kmp.template.shared.ui.components.onboarding.register.RegistrationViewModelImpl
import org.chiachat.kmp.template.shared.ui.services.NavigationService
import org.chiachat.kmp.template.shared.ui.services.ResourceService
import org.chiachat.kmp.template.shared.ui.services.ThemeService
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
