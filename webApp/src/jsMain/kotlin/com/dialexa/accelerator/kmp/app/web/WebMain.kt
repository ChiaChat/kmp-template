package com.dialexa.accelerator.kmp.app.web

import BrowserViewportWindow
import com.dialexa.accelerator.kmp.shared.WebRoot
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        val app = WebRoot()
        BrowserViewportWindow("Accelerator KMP") { app.View() }
    }
}
