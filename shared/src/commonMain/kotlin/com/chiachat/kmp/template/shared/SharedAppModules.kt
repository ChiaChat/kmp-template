package com.chiachat.kmp.template.shared

// import app.cash.sqldelight.db.SqlDriver
// import com.chiachat.kmp.template.shared.db.DbService
import co.touchlab.kermit.Logger
import com.chiachat.kmp.template.shared.services.toast.ToastService
import com.chiachat.kmp.template.shared.util.MpSettings
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
