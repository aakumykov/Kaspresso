package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Test

class AdbTest : TestCase() {

    @Test
    fun adbDevices() {
        val res = adbServer.performAdb("devices")
        Assert.assertTrue(
            res.first().contains("emulator")
        )
    }

    @Test
    fun adbListPackages() {
        /*val res = adbServer.performShell("pm list packages")
        Assert.assertTrue(
            device.targetContext.packageName in res.first()
        )*/

        val packages = adbServer.performShell("pm list packages")
        Assert.assertTrue(device.targetContext.packageName in packages.first())
    }

    @Test
    fun adbAmd() {
        Assert.assertTrue(
            "Andrey" in adbServer.performCmd("echo %USERNAME%")
        )
    }
}
