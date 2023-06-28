package com.dialexa.app.ui.components.onboarding.register

import androidx.compose.runtime.Composable
import com.dialexa.app.ui.components.Component

internal class RegistrationComponent : Component {
  override val vm: RegistrationViewModel = RegistrationViewModelImpl()

  @Composable
  override fun View() {
    RegistrationComposables.RegistrationScreen(vm::onCreate)
  }
}
