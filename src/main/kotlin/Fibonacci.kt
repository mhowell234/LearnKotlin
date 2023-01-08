class Fibonacci {
    fun generateFibo(num: Int): List<Number> {
        return fiboSequence().take(num).toList()
    }

    companion object FiboHelper {
        fun fiboSequence() = sequence {
            var terms = Pair(0L, 1L)

            while (true) {
                yield(terms.first)
                terms = terms.second to terms.first + terms.second
            }
        }
    }
}

fun main() {
    val fibonacci = Fibonacci()

    listOf(80).forEach { println(fibonacci.generateFibo(it)) }
}