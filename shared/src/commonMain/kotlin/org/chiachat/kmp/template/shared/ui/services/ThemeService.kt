package org.chiachat.kmp.template.shared.ui.services

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.kmp.template.shared.util.MpSettings

internal class ThemeService(settings: MpSettings) {
    private val darkMode = MutableStateFlow(settings.darkMode)

    fun toggleDarkTheme(isSystemInDarkTheme: Boolean) {
        val darkModeSetting = darkMode.value
        if (darkModeSetting == null) {
            darkMode.value = !isSystemInDarkTheme
        } else darkMode.value = !darkModeSetting
    }

    @Composable
    fun isDarkMode(): Boolean {
        val isDarkMode by darkMode.collectAsState()
        return isDarkMode ?: isSystemInDarkTheme()
    }
}
