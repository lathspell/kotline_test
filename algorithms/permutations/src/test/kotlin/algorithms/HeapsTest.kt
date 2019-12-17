package algorithms

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(value = Parameterized::class)
class HeapsTest(private val name: String, private val impl: PermutationAlgorithm) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "using {0}")
        fun data(): List<Array<Any>> = listOf(
                arrayOf("Heap's", Heaps),
                arrayOf("Heap's v2", Heaps2))
    }

    @Test
    fun `empty input gives empty output`() = assertEquals(emptySet(), impl.permute<Int>())

    @Test
    fun `one element input gives one element output`() = assertEquals(setOf(listOf(1)), impl.permute(1))

    @Test
    fun `two elements`() {
        val expected = setOf(listOf(1, 2), listOf(2, 1))
        assertEquals(expected, impl.permute(1, 2))
    }

    @Test
    fun `three elements`() {
        val expected = setOf(
                listOf(1, 2, 3), listOf(1, 3, 2),
                listOf(2, 1, 3), listOf(2, 3, 1),
                listOf(3, 1, 2), listOf(3, 2, 1))
        assertEquals(expected, impl.permute(1, 2, 3))
    }

    @Test
    fun `four elements`() {
        val expected = setOf(
                listOf(1, 2, 3, 4), listOf(2, 1, 3, 4), listOf(3, 1, 2, 4), listOf(1, 3, 2, 4),
                listOf(2, 3, 1, 4), listOf(3, 2, 1, 4), listOf(4, 2, 3, 1), listOf(2, 4, 3, 1),
                listOf(3, 4, 2, 1), listOf(4, 3, 2, 1), listOf(2, 3, 4, 1), listOf(3, 2, 4, 1),
                listOf(4, 1, 3, 2), listOf(1, 4, 3, 2), listOf(3, 4, 1, 2), listOf(4, 3, 1, 2),
                listOf(1, 3, 4, 2), listOf(3, 1, 4, 2), listOf(4, 1, 2, 3), listOf(1, 4, 2, 3),
                listOf(2, 4, 1, 3), listOf(4, 2, 1, 3), listOf(1, 2, 4, 3), listOf(2, 1, 4, 3))
        assertEquals(expected, impl.permute(1, 2, 3, 4))
    }
}
