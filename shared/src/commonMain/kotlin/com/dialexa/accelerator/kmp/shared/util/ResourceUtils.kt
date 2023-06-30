package com.dialexa.accelerator.kmp.shared.util

import androidx.compose.ui.graphics.ImageBitmap
import korlibs.io.file.VfsFile

expect suspend fun VfsFile.readImageBitmap(): ImageBitmap
