package com.kaspersky.kaspresso.tutorial

import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val grantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @Before
    fun revokeCallPrivilege(){
//        adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.CALL_PHONE")
    }

    @Test
    fun checkSuccessCall() = run {
        step("Открытие экрана звонков") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Проверка видимости элементов") {
            MakeCallActivityScreen {
                inputNumber {
                    isVisible()
                    hasHint(R.string.phone_number_hint)
                    hasEmptyText()
                }
                makeCallButton {
                    isVisible()
                    isClickable()
                    hasText(R.string.make_call_btn)
                }
            }
        }
        step("Звонок") {
            MakeCallActivityScreen {
                inputNumber.replaceText("+71234567890")
                makeCallButton.click()
            }
        }
        step("Проверка, что звонок осуществляется") {
            val audioManager = device.context.getSystemService(AudioManager::class.java)
            Assert.assertTrue(AudioManager.MODE_IN_CALL == audioManager.mode)
        }
    }
}
