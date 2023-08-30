plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ktfmt)
    alias(libs.plugins.kover)
}

kotlin {
    android()
    jvm("desktop")
    js(IR) {
        browser() { }
    }

    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared code for the sample"
        homepage = "https://github.com/JetBrains/compose-jb"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.material3)

                api(libs.decompose)
                api(libs.kermit)
                api(libs.kotlinx.coroutines.core)
                api(libs.koin.core)
                api(libs.korio)
                api(libs.mpsettings)
                api(libs.mpsettingsNoArgs)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.test)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.ktx)
                implementation(libs.driver.sqlite.android)
            }
        }
        val iosMain by getting {
            dependencies {
                api(libs.driver.sqlite.native)
            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.driver.sqlite.jvm)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.driver.sqlite.js)
            }
        }
        val jsTest by getting {}
    }
}


sqldelight {
    val group = properties["group"] as String
    databases {
        create(extra["camelCaseName"] as String + "Db") { // This will be the name of the generated database class.
            packageName.set("$group.shared.db")
            dialect(libs.dialect.sqlite)
            deriveSchemaFromMigrations.set(true)
            verifyMigrations.set(true)
            generateAsync.set(true)
        }
        linkSqlite.set(true)
    }
}

val group: String by project
val androidSdkVersion: String by project
val buildTools: String by project
android {
    compileSdk = androidSdkVersion.toInt()
    buildToolsVersion = buildTools
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].assets.srcDirs("src/commonMain/resources")
    namespace = "$group.shared"
    defaultConfig {
        minSdk = 24
        targetSdk = androidSdkVersion.toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
}

compose {
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.5.3")
}