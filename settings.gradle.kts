pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }
}

val artifactName: String by settings
rootProject.name = artifactName

include(":androidApp")
include(":shared")
include(":desktopApp")
include(":webApp")

