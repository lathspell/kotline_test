package algorithms

/** Heap's Algorithm as seen on https://en.wikipedia.org/wiki/Heap%27s_algorithm. */
object Heaps : PermutationAlgorithm {

    override fun <T : Comparable<T>> permute(vararg input: T): Set<List<T>> = permute(input.size, input.toMutableList())

    private fun <T : Comparable<T>> permute(k: Int, input: MutableList<T>): Set<List<T>> {
        if (k == 1) {
            // Return a new list object, not the one received as it was a mutable object and will be modified in swap()
            return setOf(input.toList())
        }

        val output = mutableSetOf<List<T>>()
        (0 until k).forEach { i ->
            val new = permute(k - 1, input)
            output.addAll(new)

            if (isEven(k)) {
                swap(input, i, k-1)
            } else {
                swap(input, 0, k-1)
            }
        }

        return output
    }

    private fun isEven(i: Int) = ((i % 2) == 0)

    private fun <T> swap(input: MutableList<T>, a: Int, b: Int) {
        val tmp = input[a]
        input[a] = input[b]
        input[b] = tmp
    }
}
