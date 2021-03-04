package de.lathspell.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PyramidTest {

    fun pyramid(height: Int) = (1..height).map { " ".repeat(height - it) + "*".repeat(2 * it - 1) }

    @Test
    fun testPyramid() {
        assertThat(pyramid(0)).contains();
        assertThat(pyramid(1)).contains("*");
        assertThat(pyramid(2)).contains(" *", "***");
        assertThat(pyramid(3)).contains("  *", " ***", "*****")
    }

}
