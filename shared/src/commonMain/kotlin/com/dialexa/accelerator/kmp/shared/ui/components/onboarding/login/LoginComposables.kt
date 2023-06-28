package com.dialexa.accelerator.kmp.shared.ui.components.onboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dialexa.accelerator.kmp.shared.ui.composables.ActionButtonColors
import com.dialexa.accelerator.kmp.shared.ui.composables.AuthInputs
import com.dialexa.accelerator.kmp.shared.ui.composables.Graphics
import com.dialexa.accelerator.kmp.shared.ui.composables.Inputs.BackButton
import com.dialexa.accelerator.kmp.shared.ui.composables.Inputs.CchActionButton

internal object LoginComposables {

    @Composable
    fun LoginScreen(onLogin: () -> Unit, onBack: () -> Unit) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize().padding(40.dp)) {
            BackButton(onBack, modifier = Modifier.align(Alignment.TopStart))
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(0.75f).align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Graphics.Logo()
                Spacer(Modifier.height(16.dp))
                AuthInputs.Username(username, onUsernameChange = { username = it })
                Spacer(Modifier.height(16.dp))
                AuthInputs.Password(password, onPasswordChange = { password = it })
                Spacer(Modifier.height(32.dp))
                CchActionButton("Login", ActionButtonColors.SECONDARY, Modifier, onLogin)
            }
        }
    }
}
