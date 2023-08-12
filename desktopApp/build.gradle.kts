import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.ktfmt)
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


val artifactName: String by project
val version: String by project
val group: String by project

compose {
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.5.1")
    desktop {
        application {
            mainClass = "${group}.app.desktop.MainKt"

            buildTypes.release.proguard {
                this.isEnabled.set(true)
                configurationFiles.from(project.file("compose-desktop.pro"))
            }

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                modules("java.sql")
                packageName = artifactName
                packageVersion = version

                windows {
                    menu = true
                    // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                    upgradeUuid = "f850ec60-faad-435c-944c-8bf566d5ffa8"

                    iconFile.set(ico)
                }

                macOS {
                    bundleID = "$group.app.desktop"
                    iconFile.set(icns)
                }

                linux {
                    iconFile.set(png)
                    debMaintainer = "andrea.bueide@dialexa.com"
                }
            }
        }
    }
}
