plugins {
    alias(libs.plugins.ktfmt)
    alias(libs.plugins.kover)
    alias(libs.plugins.taskinfo)
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.cocoapods) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

subprojects {
    tasks.withType<com.ncorti.ktfmt.gradle.tasks.KtfmtCheckTask>().configureEach {
        inputs.files.filter { !it.path.contains("build/generated") }
    }

    plugins.withType<com.ncorti.ktfmt.gradle.KtfmtPlugin>().configureEach {
        ktfmt {
            kotlinLangStyle()
        }
    }
}