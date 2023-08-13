package org.chiachat.kmp.template.shared.ui.components

import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

interface ViewModel {
    val vmScope: CoroutineScope
    val ioScope: CoroutineScope
}

abstract class KoinViewModel : ViewModel, KoinComponent {
    override val vmScope: CoroutineScope by inject(named("vmScope"))
    override val ioScope: CoroutineScope by inject(named("ioScope"))
}
