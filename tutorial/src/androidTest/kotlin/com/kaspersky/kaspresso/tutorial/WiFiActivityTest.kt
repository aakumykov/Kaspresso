package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
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
        before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.run {
            step("Открытие экрана проверки WiFi") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            WifiScreen {
                    step("Проверка начального состояния экрана") {
                        wifiStatus.hasEmptyText()
                        checkWifiButton.isVisible()
                    }
                    step("Отключение WiFi и нажатие кнопки проверки статуса") {
                        checkWifiButton {
                            device.network.toggleWiFi(false)
                            click()
                        }
                    }
                    step("Проверка, что статус WiFi 'отключен'") {
                        wifiStatus {
                            isVisible()
                            hasText(R.string.disabled_status)
                        }
                    }
            }
            step("Установка альбомной ориентации") {
                device.exploit.setOrientation(Exploit.DeviceOrientation.Landscape)
            }
            WifiScreen {
                step("Проверка, что после поворота текст 'отключен' сохранился") {
                    wifiStatus {
                        isVisible()
                        hasText(R.string.disabled_status)
                    }
                }
                step("Включение WiFi и нажатие кнопки проверки статуса") {
                    checkWifiButton {
                        device.network.toggleWiFi(true)
                        TimeUnit.SECONDS.sleep(1)
                        click()
                    }
                }
                step("Проверка на строку 'вкллючено'") {
                    wifiStatus {
                        isVisible()
                        hasText(R.string.enabled_status)
                    }
                }

            }
            step("Установка портретной ориентации") {
                device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            }
            WifiScreen {
                step("Проверка, что текст 'включено' сохранился") {
                    wifiStatus {
                        isVisible()
                        hasText(R.string.enabled_status)
                    }
                }
            }
        }
    }
}
