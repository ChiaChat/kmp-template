package org.chiachat.kmp.template.shared.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import korlibs.io.file.VfsFile
import org.jetbrains.skia.Image.Companion.makeFromEncoded

actual suspend fun VfsFile.readImageBitmap(): ImageBitmap {
    return makeFromEncoded(readAll()).toComposeImageBitmap()
}
