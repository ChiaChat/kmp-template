import java.nio.file.Files
import java.nio.file.Path

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
    group = properties["group"]!!
    version = properties["version"]!!

    tasks.withType<com.ncorti.ktfmt.gradle.tasks.KtfmtCheckTask>().configureEach {
        inputs.files.filter { !it.path.contains("build/generated") }
    }

    plugins.withType<com.ncorti.ktfmt.gradle.KtfmtPlugin>().configureEach {
        ktfmt {
            kotlinLangStyle()
        }
    }
}

val OLD_PACKAGE_PATH = "com.dialexa.accelerator.kmp".replace('/', File.separatorChar)
val OLD_ARTIFACT = "accelerator-kmp"
val OLD_CAMEL_CASE = "AcceleratorKmp"
val OLD_TITLE = "Accelerator KMP"
val OLD_FILE_NAME = "AcceleratorKmp"

tasks.register("updateProperties") {
    doLast {
        val oldPackageDirective = OLD_PACKAGE_PATH.replace(File.separatorChar, '.')
        val oldPackageRegex = Regex("\\b$oldPackageDirective\\b")

        // Read new group from gradle.properties
        val newGroup = getProperty("group").replace('.', File.separatorChar)
        val newPackagePath = newGroup.replace('.', File.separatorChar)
        val newPackageDirective = newGroup.replace(File.separatorChar, '.')

        val artifactName = getProperty("artifactName")
        val camelCaseName = getProperty("camelCaseName")
        val titleName = getProperty("titleName")

        // Rename directories
        val rootDir = project.rootDir
        rootDir.walk().filter { !it.path.contains(".git") && it.extension != "kts" }
            .forEach { file ->
                if (file.isFile) {
                    updateFile(
                        file = file,
                        old = oldPackageRegex,
                        new = newPackageDirective,
                        artifactName = artifactName,
                        camelCaseName = camelCaseName,
                        titleName = titleName
                    )
                }
                if (file.path.contains(OLD_PACKAGE_PATH)) {
                    moveFile(file = file, old = OLD_PACKAGE_PATH, new = newPackagePath)
                }
            }
        // Remove old empty directories
        removeEmptyDirs(rootDir.toPath())
    }
}

fun getProperty(propertyName: String): String {
    return properties[propertyName]?.toString()
        ?: error("$propertyName not specified in gradle.properties")
}

fun moveFile(file: File, old: String, new: String) {
    val newFilePath = file.path.replace(old, new)
    val newFile = File(newFilePath)

    // Make sure the directory exists
    newFile.parentFile.mkdirs()

    // Move the file
    println("Moving file ${file.path} to $newFilePath")
    Files.move(file.toPath(), newFile.toPath())
}

fun updateFile(
    file: File,
    old: Regex,
    new: String,
    artifactName: String,
    camelCaseName: String,
    titleName: String
) {
    var text = file.readText()
    text = text.replace(old, new)
    text = text.replace(OLD_ARTIFACT, artifactName)
    text = text.replace(OLD_CAMEL_CASE, camelCaseName)
    text = text.replace(OLD_TITLE, titleName)
    text = text.replace(OLD_FILE_NAME, camelCaseName)

    if (file.readText() != text) {
        println("Updating package directives, import statements, references in file ${file.path}")
        file.writeText(text)
    }
}

fun removeEmptyDirs(path: Path) {
    if (Files.isDirectory(path)) {
        Files.list(path).forEach { removeEmptyDirs(it) }
        if (Files.list(path).count() == 0L) {
            Files.delete(path)
            println("Deleted empty directory $path")
        }
    }
}

koverReport {
    defaults {}
}

