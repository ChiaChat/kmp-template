plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ktfmt)
}

val resPath = "src/commonMain/resources"

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.html.core)
                implementation(kotlin("stdlib-js"))
                implementation(devNpm("copy-webpack-plugin", "11.0.0"))
                implementation(npm("sql.js", "1.6.2"))
            }
            this.resources.setSrcDirs(
                listOf(
                    project(":shared").file(resPath),
                    project.file("src/jsMain/resources")
                )
            )
        }
        val jsTest by getting
    }
}

compose {
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.5.1")
    experimental {
        web.application {}
    }
}