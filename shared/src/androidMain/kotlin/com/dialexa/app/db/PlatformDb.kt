package com.dialexa.app.db

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dialexa.app.AcceleratorDb
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class PlatformDb : KoinComponent {
  val context: Context by inject()
  actual suspend fun getDriver(): SqlDriver {
    val driver: SqlDriver =
        AndroidSqliteDriver(AcceleratorDb.Schema.synchronous(), context, "dialexa.db")
    return driver
  }
}
