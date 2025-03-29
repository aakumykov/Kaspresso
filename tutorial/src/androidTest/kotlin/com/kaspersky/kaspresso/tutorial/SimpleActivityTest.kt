package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.SimpleActivityScreen
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Rule
import org.junit.Test


class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun main_screen_test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
                containsText("Simple test")
                click()
            }
        }
        SimpleActivityScreen {
            simpleTitle {
                isVisible()
                hasText(R.string.simple_activity_default_title)
            }
            inputText {
                isVisible()
                containsText("")
                hasHint(R.string.simple_activity_input_hint)
            }
            changeTitleButton {
                isVisible()
                hasText(R.string.simple_activity_change_title_button)
            }
        }
    }
}
