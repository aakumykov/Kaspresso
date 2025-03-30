package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val notificationTitle = UiTextView { withId("android", "title") }
    val notificationContent = UiTextView { withId("android", "text") }
}
