package com.kaspersky.kaspresso.tutorial.screen

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object NoteListScreen : KScreen<NoteListScreen>() {
    override val layoutId: Int?
        get() = null
    override val viewClass: Class<*>?
        get() = null

    val rvNotes = KRecyclerView(
        builder = { withId(R.id.rv_notes) },
        itemTypeBuilder = { itemType(::NoteItemScreen) }
    )

    class NoteItemScreen(matcher: Matcher<View>): KRecyclerItem<NoteItemScreen>(matcher) {
        val noteContainer = KView(matcher) { withId(R.id.note_container) }
        val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
        val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }
    }
}
