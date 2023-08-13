package org.chiachat.kmp.template.shared.ui.services

import androidx.compose.ui.graphics.ImageBitmap
import org.chiachat.kmp.template.shared.ui.theme.AppGraphics
import org.chiachat.kmp.template.shared.util.readImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResourceService(private val ioScope: CoroutineScope) {

    private val iconCache = MutableStateFlow<Map<AppGraphics, ImageBitmap>>(emptyMap())

    fun loadGraphic(icon: AppGraphics, onLoad: (ImageBitmap) -> Unit) {
        val cachedIcon = iconCache.value[icon]
        if (cachedIcon != null) {
            onLoad(cachedIcon)
        } else {
            ioScope.launch {
                val bitmap: ImageBitmap = icon.file.readImageBitmap()
                iconCache.value += icon to bitmap
                onLoad(bitmap)
            }
        }
    }

    fun clearCache() {
        iconCache.value = emptyMap()
    }
}
