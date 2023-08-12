import java.nio.file.Files
import java.nio.file.Path
import java.io.IOException

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

    plugins.withType<com.ncorti.ktfmt.gradle.KtfmtPlugin>().configureEach {
        ktfmt {
            kotlinLangStyle()
        }
    }
}


koverReport {
    defaults {}
}

/*
* artifactName=kmp-template
camelCaseName=KmpTemplate
titleName=KMP Template
group=com.chiachat.kmp.template
version=1.0.0
* */
val OLD_PACKAGE_PATH = "com.dialexa.accelerator.kmp".replace('.', File.separatorChar)
val OLD_PACKAGE_DIRECTIVE = "com.dialexa.accelerator.kmp"
val OLD_ARTIFACT = "kmp-template"
val OLD_CAMEL_CASE = "KmpTemplate"
val OLD_TITLE = "KMP Template"
val OLD_FILE_NAME = "KmpTemplate"
val EXCLUDED_PATHS = setOf(".git", ".gradle")

tasks.register("updateProperties") {
    doLast {
        val newGroupPath = getProperty("group").replace('.', File.separatorChar)
        val newPackageDirective = getProperty("group")

        val artifactName = getProperty("artifactName")
        val camelCaseName = getProperty("camelCaseName")
        val titleName = getProperty("titleName")

        // Filter out unnecessary files
        val filesToProcess = project.rootDir.walk()
            .filterNot { it.path.containsAny(EXCLUDED_PATHS) }
            .filterNot { it.extension == "kts" }
            .toList()

        // Process each file
        filesToProcess.forEach { file ->
            if (file.isFile) {
                updateFile(file, OLD_PACKAGE_DIRECTIVE, newPackageDirective, artifactName, camelCaseName, titleName)
            }
            if (file.path.contains(OLD_PACKAGE_PATH)) {
                moveFile(file, OLD_PACKAGE_PATH, newGroupPath)
            }
        }

        // Remove old empty directories
        removeEmptyDirs(project.rootDir.toPath())
    }
}

fun updateFile(
    file: File,
    oldPackageDirective: String,
    newPackageDirective: String,
    artifactName: String,
    camelCaseName: String,
    titleName: String
) {
    try {
        val text = file.readText()

        val updatedForPackages = updatePackageDirectives(text, oldPackageDirective, newPackageDirective)
        val updatedForNames = updateNames(updatedForPackages, artifactName, camelCaseName, titleName)
        val updatedForFilePaths = updateFilePathReferences(updatedForNames, newPackageDirective)

        if (text != updatedForFilePaths) {
            println("Updating content in file ${file.path}")
            file.writeText(updatedForFilePaths)
        }
    } catch (e: IOException) {
        println("WARNING: Couldn't update ${file.path}. Possible reason: File might be in use or other IO issues. Error: ${e.message}")
    }
}

fun updatePackageDirectives(text: String, oldPackageDirective: String, newPackageDirective: String): String {
    return text.replace(oldPackageDirective, newPackageDirective)
}

fun updateNames(text: String, artifactName: String, camelCaseName: String, titleName: String): String {
    return text
        .replace(OLD_ARTIFACT, artifactName)
        .replace(OLD_CAMEL_CASE, camelCaseName)
        .replace(OLD_TITLE, titleName)
        .replace(OLD_FILE_NAME, camelCaseName)
}

fun updateFilePathReferences(text: String, newPackageDirective: String): String {
    return text.replace(OLD_PACKAGE_PATH, newPackageDirective.replace('.', File.separatorChar))
}

fun getProperty(propertyName: String): String {
    return properties[propertyName]?.toString()
        ?: error("$propertyName not specified in gradle.properties")
}

fun moveFile(file: File, old: String, new: String) {
    val newFilePath = file.path.replace(old, new)
    val newFile = File(newFilePath)
    try {
        newFile.parentFile.mkdirs()
        println("Moving file ${file.path} to $newFilePath")
        Files.move(file.toPath(), newFile.toPath())
    } catch (e: IOException) {
        println("WARNING: Couldn't move ${file.path}. Possible reason: File might be in use or other IO issues. Error: ${e.message}")
    }
}

fun removeEmptyDirs(path: Path) {
    try {
        if (Files.isDirectory(path)) {
            Files.list(path).forEach { removeEmptyDirs(it) }
            Files.newDirectoryStream(path).use {
                if (!it.iterator().hasNext()) {
                    Files.delete(path)
                    println("Deleted empty directory $path")
                }
            }
        }
    } catch (e: IOException) {
        println("WARNING: Couldn't delete directory $path. Possible reason: Directory might be in use or other IO issues. Error: ${e.message}")
    }
}

fun String.containsAny(paths: Set<String>): Boolean {
    return paths.any { this.contains(it) }
}