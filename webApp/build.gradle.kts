plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
}

val resPath = "src/commonMain/resources"

kotlin {
    js(IR) {
        browser() {
            testTask {
                useKarma {
                    useFirefoxHeadless()
                }
            }
        }
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
    kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:1.4.6")
    experimental {
        web.application {}
    }
}

// a temporary workaround for a bug in jsRun invocation - see
// https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.0.0"
        versions.webpackCli.version = "4.10.0"
        nodeVersion = "16.0.0"
    }
}

// TODO: remove when https://youtrack.jetbrains.com/issue/KT-50778 fixed
project.tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile::class.java).configureEach {
    kotlinOptions.freeCompilerArgs += listOf("-Xir-dce-runtime-diagnostic=log")
}
