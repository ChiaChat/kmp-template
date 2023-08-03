package com.dialexa.accelerator.kmp.shared.db

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class PlatformDb : KoinComponent {
    val context: Context by inject()

    actual suspend fun getDriver(): SqlDriver {
        val driver: SqlDriver =
            AndroidSqliteDriver(AcceleratorKmpDb.Schema.synchronous(), context, "dialexa.db")
        return driver
    }
}
