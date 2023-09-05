package org.chiachat.kmp.template.shared.toast

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.chiachat.kmp.template.shared.services.toast.ToastService

class ToastTest {

    val toast1Message = "test"
    val toast2Message = "test2"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testTimer() = runTest {
        val toastService = ToastService(this)

        toastService.debug(toast1Message)
        assertEquals(toast1Message, toastService.currentToast.value?.message)
        toastService.info(toast2Message)
        assertEquals(
            toast2Message,
            toastService.currentToast.value?.message,
        )
        advanceTimeBy(3000)
        assertEquals(toast2Message, toastService.currentToast.value?.message)
        advanceTimeBy(3000)
        assertEquals(
            null,
            toastService.currentToast.value?.message,
        )
    }
}
