package com.dialexa.app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.dialexa.app.AcceleratorDb

actual class PlatformDb {
  actual suspend fun getDriver(): SqlDriver {
    val driver: SqlDriver = NativeSqliteDriver(AcceleratorDb.Schema, "dialexa.db")
    return driver
  }
}
