package com.dialexa.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dialexa.app.ui.services.NavigationService
import com.dialexa.app.ui.theme.AcceleratorTheme.AppTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ComposeRoot : KoinComponent {
    val navigationService: NavigationService by inject()

    @Composable
    fun View() {
        AppTheme() {
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                navigationService.currentView()
            }
        }
    }
}
