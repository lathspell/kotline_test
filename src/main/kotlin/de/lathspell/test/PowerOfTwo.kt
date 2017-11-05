package de.lathspell.test

class PowerOfTwo {
    companion object {
        @JvmStatic
        fun isPowerOfTwo(x: Int): Boolean {
            // If x is a power of two then it's a 1 with only zeroes following.
            // x-1 must then be a number with the first bit 0 and then only 1.
            // As those two have no 1 bit in common, the binary and will be 0.
            return ((x and (x - 1)) == 0)
        }
    }

}