plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.ktfmt)
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.2")
            }
        }
    }
}

android {
    val packagePath = properties["group"] as String + ".app.android"
    compileSdk = 34
    namespace = packagePath
    buildToolsVersion = "34.0.0"
    defaultConfig {
        applicationId = packagePath
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose {
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.4.6")
}

tasks.named("ktfmtFormatKmpAndroidMain") {
    dependsOn(":androidApp:ktfmtFormatMain")
}