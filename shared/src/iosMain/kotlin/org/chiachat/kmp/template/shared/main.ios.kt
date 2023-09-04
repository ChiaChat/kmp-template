package org.chiachat.kmp.template.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import org.chiachat.kmp.template.shared.ui.ComposeAppModules
import org.chiachat.kmp.template.shared.ui.ComposeRoot
import org.chiachat.kmp.template.shared.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val root = ComposeRoot()

internal val iosModule = module {
    factory(named("ioScope")) { CoroutineScope(Dispatchers.Default) }
    factory(named("vmScope")) { CoroutineScope(Dispatchers.Default) }
}

fun InitKoin() {
    startKoin {
        val composeModules = ComposeAppModules()
        val sharedModules = SharedAppModules()
        modules(iosModule + composeModules.all + sharedModules.all)
        allowOverride(false)
    }
}

fun MainViewController() = ComposeUIViewController {
    AppTheme.AppTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Spacer(Modifier.height(30.dp))
            // To skip upper part of screen.
            root.View()
        }
    }
}
