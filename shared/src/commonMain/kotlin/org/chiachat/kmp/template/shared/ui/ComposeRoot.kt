package org.chiachat.kmp.template.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.chiachat.kmp.template.shared.ui.services.NavigationService
import org.chiachat.kmp.template.shared.ui.theme.AppTheme.AppTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ComposeRoot : KoinComponent {
    val navigationService: NavigationService by inject()

    @Composable
    fun View() {
        AppTheme() {
            Box(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            ) {
                navigationService.currentView()
            }
        }
    }
}
