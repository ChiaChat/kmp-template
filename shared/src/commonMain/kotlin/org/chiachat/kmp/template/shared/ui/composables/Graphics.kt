package org.chiachat.kmp.template.shared.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import org.chiachat.kmp.template.shared.ui.services.ResourceService
import org.chiachat.kmp.template.shared.ui.theme.AppGraphics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal object Graphics : KoinComponent {

    val resources: ResourceService by inject()

    @Composable
    fun Graphic(
        graphic: AppGraphics,
        contentDescription: String,
        tint: Color = Color.Unspecified,
        modifier: Modifier = Modifier
    ) {

        var graphicBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(true) { resources.loadGraphic(graphic) { graphicBitmap = it } }

        graphicBitmap?.let {
            Icon(
                bitmap = it,
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier
            )
        }
    }

    // wraps the above function inside of an IconButton and passes in the onclick
    @Composable
    fun GraphicButton(
        graphic: AppGraphics,
        tint: Color = MaterialTheme.colorScheme.primary,
        contentDescription: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        IconButton(onClick = onClick, modifier = modifier) {
            Graphic(graphic, contentDescription, tint = tint, modifier = Modifier.size(64.dp))
        }
    }

    @Composable
    fun OutlinedGraphicButton(
        graphic: AppGraphics,
        contentDescription: String,
        outlineColor: Color = MaterialTheme.colorScheme.secondary,
        onClick: () -> Unit
    ) {
        IconButton(onClick = onClick) {
            OutlinedGraphic(graphic, contentDescription, outlineColor, Modifier.size(64.dp))
        }
    }

    @Composable
    fun OutlinedGraphic(
        graphic: AppGraphics,
        contentDescription: String,
        outlineColor: Color,
        modifier: Modifier = Modifier
    ) {
        Box() { Graphic(graphic, contentDescription, tint = outlineColor, modifier = modifier) }
    }

    @Composable
    fun Logo() {
        Graphic(AppGraphics.APP_ICON, "Project Logo", modifier = Modifier.width(300.dp))
    }
}
