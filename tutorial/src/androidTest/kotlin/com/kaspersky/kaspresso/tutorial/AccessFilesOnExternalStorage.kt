package com.kaspersky.kaspresso.tutorial

import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kaspresso.Kaspresso.Companion.DEFAULT_LIB_LOGGER_TAG
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.File

class AccessFilesOnExternalStorage : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple( // simple/advanced - it doesn't matter
        customize = {
            // storage support for Android API 30+
            if (isAndroidRuntime) {
                UiDevice
                    .getInstance(instrumentation)
                    .executeShellCommand("appops set --uid ${InstrumentationRegistry.getInstrumentation().targetContext.packageName} MANAGE_EXTERNAL_STORAGE allow")
            }
        }
    )
) {

    // storage support for Android API 29-
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )


    @Test
    fun listFileInDownloads() = run {
        step("Получение каталога Загрузки") {
            val downloadsDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

            step("Листинг каталога загрузок") {
                downloadsDir.list()!!
                    .map {  fileName ->
                        File(downloadsDir, fileName)
                    }.forEach { file ->
                        log("${if (file.isDirectory) "DIR:" else "FILE:"} ${file.absolutePath}")
                    }
            }
        }
    }


    private fun log(text: String) = Log.d(DEFAULT_LIB_LOGGER_TAG, text)


/*
    private fun grantStoragePermissionsViaAdb() {
        if (isAndroidROrLater) {
            adbServer.performShell("pm grant com.kaspersky.kaspresso.tutorial android.permission.MANAGE_EXTERNAL_STORAGE")
        } else {
            adbServer.performShell("pm grant com.kaspersky.kaspresso.tutorial android.permission.READ_EXTERNAL_STORAGE")
            adbServer.performShell("pm grant com.kaspersky.kaspresso.tutorial android.permission.WRITE_EXTERNAL_STORAGE")
        }
    }

    private fun revokeStoragePermissionsViaAdb() {
        if (isAndroidROrLater) {
            adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.MANAGE_EXTERNAL_STORAGE")
        } else {
            adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.READ_EXTERNAL_STORAGE")
            adbServer.performShell("pm revoke com.kaspersky.kaspresso.tutorial android.permission.WRITE_EXTERNAL_STORAGE")
        }
    }

    private val isAndroidROrLater: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
*/
}
