package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.tutorial.GooglePlayTest

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = GooglePlayTest.GOOGLE_PLAY_PACKAGE

    val signInButton = UiButton {
        withText("Войти")
    }
}
