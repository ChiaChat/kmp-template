package com.chiachat.kmp.template.shared.util

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import korlibs.io.file.VfsFile

actual suspend fun VfsFile.readImageBitmap(): ImageBitmap {
    return BitmapFactory.decodeByteArray(this.readAll(), 0, this.size().toInt()).asImageBitmap()
}
