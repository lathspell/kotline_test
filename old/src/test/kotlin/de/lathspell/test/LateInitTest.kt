package de.lathspell.test

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LateInitTest {
    // just to keep track of the execution order
    companion object {
        private val order = mutableListOf<String>()
    }

    // imagine a data class for application preferences
    data class Prefs(val color : String) {
        init {
            order.add("prefs-init")
        }
    }

    // imagine a loader that reads an .ini file
    class PrefsLoader {
        fun loadPrefs(): Prefs {
            order.add("loadPrefs")
            return Prefs("red")
        }
    }

    /** By lazy-loading the "prefs" variable, the preference loading is deferred until the variable is actually be accessed. */
    @Test
    fun test() {
        order.add("before")
        val prefs: Prefs by lazy {
            PrefsLoader().loadPrefs()
        }
        order.add("after")

        assertNotNull(prefs)
        assertEquals(listOf("before", "after", "loadPrefs", "prefs-init"), order)
    }
}