package org.chiachat.kmp.template.shared.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import korlibs.io.file.std.userHomeVfs

actual class PlatformDb {
    private val sqlitePath = userHomeVfs[".abysl/kmp-template/app.db"]

    actual suspend fun getDriver(): SqlDriver {
        sqlitePath.parent.mkdirs()
        return JdbcSqliteDriver("jdbc:sqlite:${sqlitePath.absolutePath}")
    }
}
