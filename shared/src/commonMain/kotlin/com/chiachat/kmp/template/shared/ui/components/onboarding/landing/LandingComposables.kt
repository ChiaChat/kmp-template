package com.chiachat.kmp.template.shared.ui.components.onboarding.landing

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chiachat.kmp.template.shared.ui.composables.ActionButtonColors
import com.chiachat.kmp.template.shared.ui.composables.Graphics
import com.chiachat.kmp.template.shared.ui.composables.Inputs

internal object LandingComposables {
    @Composable
    fun LandingScreen(onNext: (Boolean) -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(0.75f).align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Graphics.Logo()
                Inputs.CchActionButton("Login", ActionButtonColors.SECONDARY) { onNext(true) }
                Inputs.CchActionButton("Create Account", ActionButtonColors.SURFACE) {
                    onNext(false)
                }
            }
        }
    }
}
