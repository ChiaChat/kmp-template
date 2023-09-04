package org.chiachat.kmp.template.shared.ui.components

import androidx.compose.runtime.Composable
import org.koin.core.component.KoinComponent

internal interface Component : KoinComponent {
    val vm: ViewModel

    @Composable fun View()
}
