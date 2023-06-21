import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm {
        compilations.all { kotlinOptions.jvmTarget = "17" }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
}

val assets = File("../shared/src/commonMain/resources/assets" )
val icons = assets.resolve("icons/dialexa")
val ico = icons.resolve("dialexa-icon-gold.ico")
val icns = icons.resolve("dialexa-icon-gold.icns")
val png = icons.resolve("dialexa-icon-gold.png")

compose {
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.4.6")
    desktop {
        application {
            mainClass = "com.dialexa.app.desktop.MainKt"

            buildTypes.release.proguard {
                this.isEnabled.set(true)
                configurationFiles.from(project.file("compose-desktop.pro"))
            }

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "Accelerator Multiplatform"
                packageVersion = "1.0.0"
                modules("java.sql")

                windows {
                    menu = true
                    // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                    upgradeUuid = "f850ec60-faad-435c-944c-8bf566d5ffa8"

                    iconFile.set(ico)
                }

                macOS {
                    bundleID = "com.dialexa.app"
                    iconFile.set(icns)
                }

                linux {
                    iconFile.set(png)
                    packageName = "accelerator-multiplatform"
                    debMaintainer = "andrea.bueide@dialexa.com"
                }
            }
        }
    }
}
