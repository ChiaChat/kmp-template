@file:OptIn(ExperimentalMaterial3Api::class)

package com.chiachat.kmp.template.shared.ui.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chiachat.kmp.template.shared.ui.composables.Graphics.Graphic
import com.chiachat.kmp.template.shared.ui.composables.Graphics.GraphicButton
import com.chiachat.kmp.template.shared.ui.services.ThemeService
import com.chiachat.kmp.template.shared.ui.theme.AppGraphics
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

enum class ActionButtonColors {
    PRIMARY,
    SECONDARY,
    SURFACE
}

internal object Inputs : KoinComponent {
    @Composable
    fun CchTextField(
        textProperty: MutableStateFlow<String>,
        label: String? = null,
        placeholder: String? = label,
        modifier: Modifier = Modifier
    ) {
        val text: String by textProperty.collectAsState()
        OutlinedTextField(
            value = text,
            label = @Composable { label?.let { Text(it) } },
            placeholder = @Composable { placeholder?.let { Text(it) } },
            onValueChange = { newText: String -> textProperty.value = newText },
            modifier = Modifier.fillMaxWidth().then(modifier)
        )
    }

    @Composable
    fun CchActionButton(
        text: String,
        color: ActionButtonColors,
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
    ) {
        OutlinedButton(
            onClick,
            colors = CchButtonColors(color),
            modifier = Modifier.width(300.dp).then(modifier)
        ) {
            Text(text)
        }
    }

    @Composable
    fun CchButtonColors(color: ActionButtonColors): ButtonColors {
        return when (color) {
            ActionButtonColors.PRIMARY ->
                ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ActionButtonColors.SECONDARY ->
                ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ActionButtonColors.SURFACE ->
                ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
        }
    }

    val themeService: ThemeService by inject()

    @Composable
    fun ToggleDarkModeButton() {
        val systemInDarkTheme = isSystemInDarkTheme()
        IconButton(onClick = { themeService.toggleDarkTheme(systemInDarkTheme) }) {
            if (themeService.isDarkMode()) Graphic(AppGraphics.LIGHT_MODE, "Enable light mode")
            else Graphic(AppGraphics.DARK_MODE, "Enable Dark Mode")
        }
    }

    @Composable
    fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
        GraphicButton(
            graphic = AppGraphics.BACK_ARROW,
            contentDescription = "Back Button",
            tint = MaterialTheme.colorScheme.primary,
            onClick = onClick,
            modifier = Modifier.size(32.dp).then(modifier)
        )
    }
}
