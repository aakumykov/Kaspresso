package com.kaspersky.kaspresso.tutorial

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @After
    fun delay() { TimeUnit.SECONDS.sleep(1) }

    @Test
    fun testRecyclerView() = run {
        step("Открыть экран с RecyclerView") {
            MainScreen {
                listViewButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Проверка, что элементов в списке три  штуки") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Проверка видимости всех элементов списка") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }

        step("Замедление анимации") {
            setAnimationSpeed(4f)
        }
        step("Удаление первого элемента") {
            NoteListScreen {
                rvNotes {
                    firstChild<NoteListScreen.NoteItemScreen> {
                        view.perform(ViewActions.swipeLeft())
                        device.uiDevice.waitForIdle()
                    }
                    Assert.assertEquals(2, this@rvNotes.getSize())

                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    lastChild<NoteListScreen.NoteItemScreen> {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Ускорение анимации") {
            setAnimationSpeed(0.1f)
        }
    }

    private fun setAnimationSpeed(durationMultiplier: Float) {
        adbServer.performShell("settings put global window_animation_scale $durationMultiplier")
        adbServer.performShell("settings put global transition_animation_scale $durationMultiplier")
        adbServer.performShell("settings put global animator_duration_scale $durationMultiplier")
    }
}
