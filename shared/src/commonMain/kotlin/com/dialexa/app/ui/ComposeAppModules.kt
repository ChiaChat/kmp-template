package com.dialexa.app.ui

import com.dialexa.app.ui.components.onboarding.landing.LandingComponent
import com.dialexa.app.ui.components.onboarding.landing.LandingViewModelImpl
import com.dialexa.app.ui.components.onboarding.login.LoginComponent
import com.dialexa.app.ui.components.onboarding.login.LoginViewModelImpl
import com.dialexa.app.ui.components.onboarding.register.RegistrationComponent
import com.dialexa.app.ui.components.onboarding.register.RegistrationViewModelImpl
import com.dialexa.app.ui.services.NavigationService
import com.dialexa.app.ui.services.ResourceService
import com.dialexa.app.ui.services.ThemeService
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
