package de.lathspell.test

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class AspectjTest {

    @Test
    fun test1() {
        assertThat("test").isEqualTo("test")
        assertThat("test").startsWith("te")
        assertThat("test").contains("es")
        assertThat("test").hasLineCount(1)
    }

    @Test // (AssertionError::class)
    fun testFailsButWithDescription() {
        assertThat("test").`as`("check for single line").hasLineCount(2)
    }

}