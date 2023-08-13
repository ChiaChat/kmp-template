package org.chiachat.kmp.template.app.web

import BrowserViewportWindow
import org.chiachat.kmp.template.shared.WebRoot
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        val app = WebRoot()
        BrowserViewportWindow("KMP Template") { app.View() }
    }
}
