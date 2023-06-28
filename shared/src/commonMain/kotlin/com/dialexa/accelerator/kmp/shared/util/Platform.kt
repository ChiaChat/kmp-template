package com.dialexa.accelerator.kmp.shared.util

enum class Platform {
    IOS,
    ANDROID,
    JVM,
    JS
}

expect fun getPlatform(): Platform
