package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import com.kaspersky.kaspresso.tutorial.scenario.LoginScenario
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class LoginActivityTest : com.kaspersky.kaspresso.testcases.api.testcase.TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @After
    fun delay() {
        TimeUnit.SECONDS.sleep(1)
    }

    @Test
    fun successLoginTest() {
        run {
            step("Попытка входа с корректными именем и паролем") {
                scenario(
                    LoginScenario("username", "password")
                )
            }
            step("Проверка, что экран изменился на 'Авторизован'") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }

    @Test
    fun loginUnsuccessfulIfUsernameIncorrect() {
        run {
            step("Попытка входа с пустыми логином и паролем") {
                scenario(
                    LoginScenario("","")
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }

    @Test
    fun loginUnsuccessfulIfPasswordIncorrect() {
        run {
            step("Попытка входа без пароля") {
                scenario(
                    LoginScenario("user1","")
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }
}
