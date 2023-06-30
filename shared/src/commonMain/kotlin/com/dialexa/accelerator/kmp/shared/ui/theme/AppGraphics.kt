package com.dialexa.accelerator.kmp.shared.ui.theme

import korlibs.io.file.VfsFile
import korlibs.io.file.std.resourcesVfs


const val ICON_PATH = "assets/icons"

enum class AppGraphics(val file: VfsFile) {
    DARK_MODE(resourcesVfs["$ICON_PATH/material/dark_mode.png"]),
    LIGHT_MODE(resourcesVfs["$ICON_PATH/material/light_mode.png"]),
    DIALEXA_ICON(resourcesVfs["$ICON_PATH/dialexa/dialexa-icon-gold.png"]),
    NOTIFICATION_BELL(resourcesVfs["$ICON_PATH/material/notification_bell.png"]),
    MENU(resourcesVfs["$ICON_PATH/material/menu.png"]),
    BACK_ARROW(resourcesVfs["$ICON_PATH/material/backarrow.png"])
}
