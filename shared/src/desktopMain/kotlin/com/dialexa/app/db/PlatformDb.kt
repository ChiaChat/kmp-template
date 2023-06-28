package com.dialexa.app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.soywiz.korio.file.std.userHomeVfs

actual class PlatformDb {
  private val sqlitePath = userHomeVfs[".dialexa/dialexa.db"]
  actual suspend fun getDriver(): SqlDriver {
    sqlitePath.parent.mkdirs()
    return JdbcSqliteDriver("jdbc:sqlite:${sqlitePath.absolutePath}")
  }
}
