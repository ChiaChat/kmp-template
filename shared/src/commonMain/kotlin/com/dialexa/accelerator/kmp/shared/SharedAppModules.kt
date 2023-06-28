package com.dialexa.accelerator.kmp.shared

// import app.cash.sqldelight.db.SqlDriver
// import com.dialexa.accelerator.kmp.shared.db.DbService
import co.touchlab.kermit.Logger
import com.dialexa.accelerator.kmp.shared.services.toast.ToastService
import com.dialexa.accelerator.kmp.shared.util.MpSettings
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SharedAppModules() {

    val services = module {
        //    single { DbService(driver) }
        single { ToastService(ioScope = get(named("ioScope")), logger = Logger) }
        singleOf(::MpSettings)
    }

    val all = services
}
