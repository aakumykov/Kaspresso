package com.kaspersky.kaspresso.tutorial

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
    fun testRV() = run {
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
    }
}
