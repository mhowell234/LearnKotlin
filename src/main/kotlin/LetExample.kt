object LetExample {
    fun filterValues() {
        val values = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        values.filter { it.length > 3 }.let(::println)
    }
}

fun main() {
    LetExample.filterValues()
}