package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Test

class AdbTest : TestCase() {

    @Test
    fun testAdb() {
        val res = adbServer.performAdb("devices")
        Assert.assertTrue(
            res.first().contains("emulator")
        )
    }
}
