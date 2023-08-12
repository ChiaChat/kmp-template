package com.chiachat.kmp.template.shared.util

enum class Platform {
    IOS,
    ANDROID,
    JVM,
    JS
}

expect fun getPlatform(): Platform
