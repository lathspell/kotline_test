package algorithms

interface PermutationAlgorithm {
    fun <T : Comparable<T>> permute(vararg input: T): Set<List<T>>
}
