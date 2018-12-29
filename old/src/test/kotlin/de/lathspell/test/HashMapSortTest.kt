package de.lathspell.test

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class HashMapSortTest {
    @Test
    fun test() {
        val m = hashMapOf("a" to 4, "b" to 7, "c" to 2)
        assertEquals("{a=4, b=7, c=2}", m.toString())

        val sm = m.toList().sortedBy { (_, v) -> v }.toMap()
        assertEquals("{c=2, a=4, b=7}", sm.toString())
    }

    @Ignore("TODO")
    @Test
    fun testUsingJava() {
        val m = java.util.HashMap<String, Integer>()
        m.put("a", Integer(4))
        m.put("b", Integer(7))
        m.put("c", Integer(2))

        val sm: java.util.HashMap<String, Integer>
        // val sm: java.util.HashMap<String, Integer> = m.entries.stream().sorted { o1, o2 -> java.lang.Integer.compare(o1.value.toInt(), o2.value.toInt()) }.collect(Collectors.toMap( t -> t.key, t -> t.value))
        // assertEquals("{c=2, a=4, b=7}", sm.toString()) TODO
    }
}