package com.dialexa.app.desktop

import com.dialexa.app.DesktopRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

val desktopRoot = DesktopRoot()

fun main() = application {
    val icon = painterResource("assets/icons/dialexa/dialexa-icon-gold.png")
    Window(onCloseRequest = ::exitApplication, icon = icon) {
        this.window.title = "Accelerator Multiplatform"
        desktopRoot.View()
    }
}