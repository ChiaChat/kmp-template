package com.dialexa.app.web

import BrowserViewportWindow
import com.dialexa.app.WebRoot
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    val app = WebRoot()
    BrowserViewportWindow("Accelerator Multiplatform") { app.View() }
  }
}
