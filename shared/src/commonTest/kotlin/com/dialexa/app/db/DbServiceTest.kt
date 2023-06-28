package com.dialexa.app.db

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
