package com.dialexa.app.db

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import com.dialexa.app.db.DbService
import com.dialexa.app.db.PlatformDb
import com.dialexa.app.util.Platform
import com.dialexa.app.util.getPlatform
import kotlin.test.Test
import kotlin.test.assertTrue

class DbServiceTest {

    /*@OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testVersion() = runTest {
        val platform = getPlatform()
        if (platform != Platform.ANDROID && platform != Platform.JS) {
            val driver = PlatformDb().getDriver()
            val dbService = DbService(driver)
            dbService.runMigrations()
            val version = dbService.getVersion()
            assertTrue { version != null && version > 1L }
            dbService.setVersion(5)
            assertTrue { dbService.getVersion() == 5 }
            if (version != null) {
                dbService.setVersion(version)
            }
        }
    }*/
}
