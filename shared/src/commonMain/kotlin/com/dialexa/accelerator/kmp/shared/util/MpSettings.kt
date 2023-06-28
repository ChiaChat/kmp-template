package com.dialexa.accelerator.kmp.shared.util

import com.dialexa.accelerator.kmp.shared.util.SettingKeys.REFRESH_TOKEN
import com.russhwolf.settings.Settings

class MpSettings {
    val settings: Settings = Settings()

    //  var homeDir: VfsFile
    //    get() = applicationDataVfs[settings.getStringOrNull(HOME_DIR.name) ?: "."]
    //    set(value) = settings.putString(HOME_DIR.name, value.absolutePath)
    var refreshToken: String
        get() = settings.getString(REFRESH_TOKEN.name, "")
        set(value) = settings.putString(REFRESH_TOKEN.name, value)

    var darkMode: Boolean?
        get() = settings.getBooleanOrNull(SettingKeys.DARK_MODE.name)
        set(value) {
            if (value == null) settings.remove(SettingKeys.DARK_MODE.name)
            else settings.putBoolean(SettingKeys.DARK_MODE.name, value)
        }
}

enum class SettingKeys {
    REFRESH_TOKEN,
    DARK_MODE,
}
