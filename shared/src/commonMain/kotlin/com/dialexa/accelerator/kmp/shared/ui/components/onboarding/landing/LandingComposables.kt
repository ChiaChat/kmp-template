package com.dialexa.accelerator.kmp.shared.ui.components.onboarding.landing

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dialexa.accelerator.kmp.shared.ui.composables.ActionButtonColors
import com.dialexa.accelerator.kmp.shared.ui.composables.Graphics
import com.dialexa.accelerator.kmp.shared.ui.composables.Inputs

@Composable
fun LandingScreen(onNext: (Boolean) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(0.75f).align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Graphics.Logo()
            Inputs.CchActionButton("Login", ActionButtonColors.SECONDARY) { onNext(true) }
            Inputs.CchActionButton("Create Account", ActionButtonColors.SURFACE) { onNext(false) }
        }
    }
}
