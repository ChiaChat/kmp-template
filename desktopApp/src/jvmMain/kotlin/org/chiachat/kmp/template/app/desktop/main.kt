package org.chiachat.kmp.template.app.desktop

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chiachat.kmp.template.shared.DesktopRoot

val desktopRoot = DesktopRoot()

fun main() = application {
    val icon = painterResource("assets/icons/app-icon.png")
    Window(onCloseRequest = ::exitApplication, icon = icon) {
        this.window.title = "KMP Template"
        desktopRoot.View()
    }
}
