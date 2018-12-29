package de.lathspell.test

import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArrayTest {

    @Test
    fun testArrays() {
        /** Java primitives arrays - returns Java int[] */
        val ia: IntArray? = ArraysTestProvider.getIntArray()
        assertTrue(ia.toString().startsWith("[I@"))
        assertEquals("[0, 0, 0, 4]", Arrays.toString(ia))

        /** Java object arrays - returns Java Integer[] */
        val iia: Array<Int?>? = ArraysTestProvider.getIntegerArray()
        assertTrue(iia.toString().startsWith("[Ljava.lang.Integer;@"))
        // FIXME: has no toList():                 Assert.assertEquals("[0, 0, 0, 4]", iia.toList())
        // FIXME: does not compile:                Assert.assertEquals("[0, 0, 0, 4]", Arrays.toString(iia))
        // FIXME: "array<Any?> was expected":      Assert.assertEquals("[0, 0, 0, 4]", Arrays.deepToString(iia))
        // FIXME: returns "[Ljava.lang.Integer;@": Assert.assertEquals("[0, 0, 0, 4]", std.util.arrayList(iia))

        /** Java Collections (work the same in Kotlin) */
        val ci: MutableList<Int?>? = ArraysTestProvider.getCollectionArray()
        // FIXME: "unresolved reference":    Assert.assertEquals("[Ljava.lang.Integer;@", typeinfo(ci).toString())
        assertEquals("[0, 0, 0, 4]", ci.toString())

        /** Kotlin Array<primitive> */
        val kai: Array<Int> = arrayOf(0, 0, 0, 0)
        assertTrue(true)
        kai[kai.size - 1] = 4
        assertTrue(kai.toString().startsWith("[Ljava.lang.Integer;@"))
        assertEquals("[0, 0, 0, 4]", kai.toList().toString())

        // Converting
        // FIXME: val ci_from_ia : List<Int> = Arrays.asList(ia)
        // FIXME: val ci_from_iia : List<Int?> = Arrays.asList(iia) ?: ArrayList<Int?>(0)
        // FIXME: val ci_from_kai: java.util.List<Int>? = kai.toList()
        // FIXME: val ia_from_ci : IntArray = ci.toArray(ia as Array<Int>?)
        // FIXME: val ia_from_iia: IntArray = ArrayUtils.toPrimitive(iia) ?: IntArray(0)
        // FIXME; val ia_from_kai : IntArray =
        // FIXME: val kai_from_ia: Array<Int?> = ArrayUtils.toObject(ia) ?: Array<Int>(0)
        // FIXME: val kai_from_iia: Array<Int?> = iia ?: Array<Int>(0)
        // FIXME: val kai_from_ci : Array<Int> = ci.toArray(kai) // IDEA differs from Maven?!
    }
}