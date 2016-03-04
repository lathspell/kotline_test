package de.lathspell.test.kotlin;

import junit.framework.TestCase
import junit.framework.Assert

class HelloTest : TestCase() {

    fun testIt() {
        Assert.assertEquals("foo", "f" + "o" + "o")
        Assert.assertTrue(!false)
    }

}
