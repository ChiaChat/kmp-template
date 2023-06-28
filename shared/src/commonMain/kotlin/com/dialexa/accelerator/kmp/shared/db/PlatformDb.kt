package com.dialexa.accelerator.kmp.shared.db

import app.cash.sqldelight.db.SqlDriver

expect class PlatformDb() {
    suspend fun getDriver(): SqlDriver
}
