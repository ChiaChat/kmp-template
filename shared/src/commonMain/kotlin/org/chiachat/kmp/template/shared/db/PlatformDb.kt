package org.chiachat.kmp.template.shared.db

import app.cash.sqldelight.db.SqlDriver

expect class PlatformDb() {
    suspend fun getDriver(): SqlDriver
}
