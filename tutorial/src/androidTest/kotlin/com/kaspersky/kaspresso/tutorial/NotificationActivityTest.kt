package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NotificationActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.NotificationScreen
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Rule
import org.junit.Test

class NotificationActivityTest : TestCase() {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Открытие экрана уведомлений") {
            MainScreen {
                notificationActivityButton.apply {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Отображение уведомления") {
            NotificationActivityScreen {
                showNotificationButton.apply {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Проверка содержимого уведомления") {
            NotificationScreen {
                notificationTitle.apply {
                    isDisplayed()
                    hasText(getResourceString(R.string.notification_title))
                }
                notificationContent .apply {
                    isDisplayed()
                    hasText(getResourceString(R.string.notification_content))
                }
            }
        }
    }
}
