package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.tutorial.scenario.CallNumberScenario
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : com.kaspersky.kaspresso.testcases.api.testcase.TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
        adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.CALL_PHONE")
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        scenario(
            CallNumberScenario(testNumber)
        )
        step("Accept call permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }

    @Test
    fun checkCallDenied() = before {
        adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.CALL_PHONE")
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        scenario(
            CallNumberScenario(testNumber)
        )
        step("Deny call permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
            }
        }
        step("Check phone is not calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertFalse(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}
