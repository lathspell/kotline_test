package de.lathspell.test.kotlin

import java.util.Arrays
import java.util.List
import java.util.ArrayList
import java.util.Collections
import java.util.Collection

import junit.framework.Assert
import junit.framework.TestCase
import org.apache.commons.lang3.ArrayUtils

import de.lathspell.test.kotlin.ArraysTestProvider

class ArrayTest : TestCase("ArrayTest") {

    fun testArrays() {
        /** Java primitives arrays - returns Java int[] */
        val ia : IntArray? = ArraysTestProvider.getIntArray();
        Assert.assertTrue(ia.toString().startsWith("[I@"))
        Assert.assertEquals("[0, 0, 0, 4]", Arrays.toString(ia))

        /** Java object arrays - returns Java Integer[] */
        val iia : Array<Int?>? = ArraysTestProvider.getIntegerArray()
        Assert.assertTrue(iia.toString().startsWith("[Ljava.lang.Integer;@"))
        // FIXME: has no toList():                 Assert.assertEquals("[0, 0, 0, 4]", iia.toList())
        // FIXME: does not compile:                Assert.assertEquals("[0, 0, 0, 4]", Arrays.toString(iia))
        // FIXME: "array<Any?> was expected":      Assert.assertEquals("[0, 0, 0, 4]", Arrays.deepToString(iia))
        // FIXME: returns "[Ljava.lang.Integer;@": Assert.assertEquals("[0, 0, 0, 4]", std.util.arrayList(iia))

        /** Java Collections (work the same in Kotlin) */
        val ci : List<Int?>? = ArraysTestProvider.getCollectionArray();
        // FIXME: "unresolved reference":    Assert.assertEquals("[Ljava.lang.Integer;@", typeinfo(ci).toString())
        Assert.assertEquals("[0, 0, 0, 4]", ci.toString())

        /** Kotlin Array<primitive> */
        val kai : Array<Int> = array(0, 0, 0, 0)
        Assert.assertTrue(true)
        kai[kai.size -1] = 4
        Assert.assertTrue(kai.toString().startsWith("[Ljava.lang.Integer;@"))
        Assert.assertEquals("[0, 0, 0, 4]", kai.toList().toString())

        // Converting
        // FIXME: val ci_from_ia : List<Int> = Arrays.asList(ia)
        // FIXME: val ci_from_iia : List<Int?> = Arrays.asList(iia) ?: ArrayList<Int?>(0)
        val ci_from_kai : List<Int>? = kai.toList()
        // FIXME: val ia_from_ci : IntArray = ci.toArray(ia as Array<Int>?)
        val ia_from_iia : IntArray = ArrayUtils.toPrimitive(iia) ?: IntArray(0)
        // FIXME val ia_from_kai : IntArray =
        val kai_from_ia : Array<Int?> = ArrayUtils.toObject(ia) ?: Array<Int>(0)
        val kai_from_iia : Array<Int?> = iia ?: Array<Int>(0)
        // FIXME: val kai_from_ci : Array<Int> = ci.toArray(kai) // IDEA differs from Maven?!
    }
}
