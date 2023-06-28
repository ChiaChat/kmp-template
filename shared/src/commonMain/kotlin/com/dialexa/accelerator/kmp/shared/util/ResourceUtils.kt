package com.dialexa.accelerator.kmp.shared.util

import androidx.compose.ui.graphics.ImageBitmap
import com.soywiz.korio.file.VfsFile

expect suspend fun VfsFile.readImageBitmap(): ImageBitmap
