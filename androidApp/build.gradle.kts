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


val group: String by project
val androidSdkVersion: String by project
val buildTools: String by project

android {
    val packagePath = "$group.app.android"
    compileSdk = androidSdkVersion.toInt()
    namespace = packagePath
    buildToolsVersion = buildTools
    defaultConfig {
        applicationId = packagePath
        minSdk = 24
        targetSdk = androidSdkVersion.toInt()
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