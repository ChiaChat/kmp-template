package com.chiachat.kmp.template.app.web

import BrowserViewportWindow
import com.chiachat.kmp.template.shared.WebRoot
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        val app = WebRoot()
        BrowserViewportWindow("KMP Template") { app.View() }
    }
}
