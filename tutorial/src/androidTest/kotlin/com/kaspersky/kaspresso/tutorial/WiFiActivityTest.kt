package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import kotlinx.coroutines.delay
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class WiFiActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testWifiStatus() {
        MainScreen {
            wifiActivityButton {
                isVisible()
                isClickable()
                click()
            }
        }
        WifiScreen {
            checkWifiButton {
                device.network.toggleWiFi(false)
                click()
            }
            wifiStatus {
                isVisible()
                hasText(R.string.disabled_status)
            }

            checkWifiButton {
                device.network.toggleWiFi(true)
                click()
            }
            wifiStatus {
                isVisible()
                hasText(R.string.disabled_status)
            }
        }
    }
}
