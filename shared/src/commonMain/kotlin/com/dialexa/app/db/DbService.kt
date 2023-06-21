package com.dialexa.app.db

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.dialexa.app.AcceleratorDb

class DbService(val driver: SqlDriver) {

    var db: AcceleratorDb = AcceleratorDb.invoke(driver)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            runMigrations()
        }
    }

    @Suppress("TooGenericExceptionCaught")
    fun getVersion(): Int? {
        return try {
            db.versionTableQueries.getVersion().executeAsOneOrNull()?.toInt()
        } catch (e: Exception) {
            1
        }
    }

    suspend fun setVersion(version: Int): Boolean {
        val setVersionSucceeded =
            try {
                val tableVersion = db.versionTableQueries.getVersion().executeAsOneOrNull()
                if (tableVersion == null) {
                    db.versionTableQueries.setVersion(version.toLong())
                } else {
                    db.versionTableQueries.updateVersion(version.toLong())
                }
                true
            } catch (e: Exception) {
                Logger.e { e.stackTraceToString() }
                false
            }
        return setVersionSucceeded
    }

    @Suppress("TooGenericExceptionCaught")
    suspend fun runMigrations() {
        val currentVersion = getVersion()
        val latestVersion = AcceleratorDb.Schema.version
        if (currentVersion == null) {
            // if table exists, assume latest version, try to recover
            if (setVersion(latestVersion)) runMigrations() else throw MigrationException()
        } else {
            if (currentVersion != latestVersion) {
                AcceleratorDb.Schema.migrate(driver, currentVersion, latestVersion)
                if (!setVersion(latestVersion)) throw FailedToSetDbVersionException()
            }
        }
    }
}

class FailedToSetDbVersionException : Exception()

class MigrationException : Exception("Critical Error: Failed to run database migration")
