package cz.mtrakal.bscdemo.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class NoteTest {

    @Test
    fun testToString() {
        assertEquals("Note #1: Pokus", Note(1, "Pokus").toString())
        assertNotEquals("Note #2: Pokus", Note(1, "Pokus").toString())
    }
}