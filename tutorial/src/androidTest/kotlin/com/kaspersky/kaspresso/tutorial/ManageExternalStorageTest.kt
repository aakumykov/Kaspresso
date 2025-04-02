package com.kaspersky.kaspresso.tutorial

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class ManageExternalStorageTest : TestCase() {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @After
    fun delayAfterTest() = TimeUnit.SECONDS.sleep(2)


    @Test
    fun fileInDownloadsAreAvailable() = before {

    }.after {

    }.run {

    }


    @Test
    fun testOpeningManageAllFilesScreen() = run {
        step("Запуск экрана через Intent") {

            if (!isAndroidROrLater())
                throw IllegalStateException("Не применимо к $androidVersionName")

            with(device.targetContext) {
                startActivity(
                    Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                        data = Uri.parse("package:${packageName}")
//                        setFlags(FLAG_ACTIVITY_NEW_TASK)
                    }
                )
            }

            TimeUnit.SECONDS.sleep(4)
        }
    }

    private fun isAndroidROrLater(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    private val androidVersionName
        get() = "Android ${Build.VERSION.SDK_INT} (${Build.VERSION.CODENAME})"
}
