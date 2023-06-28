pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }
}

rootProject.name = "accelerator-multiplatform"

include(":androidApp")
include(":shared")
include(":desktopApp")
include(":webApp")
