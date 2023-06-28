package com.dialexa.accelerator.kmp.shared.db

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbService(val driver: SqlDriver) {

    var db: AcceleratorKmpDb = AcceleratorKmpDb.invoke(driver)

    init {
        CoroutineScope(Dispatchers.Default).launch { runMigrations() }
    }

    @Suppress("TooGenericExceptionCaught")
    fun getVersion(): Long? {
        return try {
            db.versionTableQueries.getVersion().executeAsOneOrNull()
        } catch (e: Exception) {
            1
        }
    }

    suspend fun setVersion(version: Long): Boolean {
        val setVersionSucceeded =
            try {
                val tableVersion = db.versionTableQueries.getVersion().executeAsOneOrNull()
                if (tableVersion == null) {
                    db.versionTableQueries.setVersion(version)
                } else {
                    db.versionTableQueries.updateVersion(version)
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
        val latestVersion = AcceleratorKmpDb.Schema.version
        if (currentVersion == null) {
            // if table exists, assume latest version, try to recover
            if (setVersion(latestVersion)) runMigrations() else throw MigrationException()
        } else {
            if (currentVersion != latestVersion) {
                AcceleratorKmpDb.Schema.migrate(driver, currentVersion, latestVersion)
                if (!setVersion(latestVersion)) throw FailedToSetDbVersionException()
            }
        }
    }
}

class FailedToSetDbVersionException : Exception()

class MigrationException : Exception("Critical Error: Failed to run database migration")
