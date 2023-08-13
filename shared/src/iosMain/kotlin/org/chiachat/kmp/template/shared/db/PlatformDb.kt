package org.chiachat.kmp.template.shared.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class PlatformDb {
    actual suspend fun getDriver(): SqlDriver {
        val driver: SqlDriver =
            NativeSqliteDriver(KmpTemplateDb.Schema.synchronous(), "app.db")
        return driver
    }
}
