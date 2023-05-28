object StringProblems {

    fun firstNonRepeatingCharNestedLoop(input: String?): Char? {
        if (input.isNullOrEmpty()) {
            return null
        }

        for (outer in input.indices) {
            var isRepeated = false
            for (inner in input.indices) {
                if (inner != outer && input[inner] == input[outer]) {
                    isRepeated = true
                    break
                }
            }

            if (!isRepeated) {
                return input[outer]
            }
        }

        return null
    }

    fun firstNonRepeatingLinkedHashMap(input: String?): Char? {

        if (input.isNullOrEmpty()) {
            return null
        }

        val frequencies = LinkedHashMap<Char, Int>()

        input.codePoints().forEach {
            if (frequencies.computeIfPresent(it.toChar()) { _, v -> v + 1 } == null) {
                frequencies[it.toChar()] = 1
            }
        }

        return frequencies.filter { it.value == 1 }.firstNotNullOfOrNull { it.key }
    }

    fun countDuplicateChars(input: String?): Map<Char, Int> {
        if (input.isNullOrEmpty()) {
            return mapOf()
        }

        val frequencies = mutableMapOf<Char, Int>()

        input.codePoints().forEach {
            if (frequencies.computeIfPresent(it.toChar()) { _, v -> v + 1 } == null) {
                frequencies[it.toChar()] = 1
            }
        }

        return frequencies
    }

    fun countDuplicateCharsGroupingBy(inputString: String): Map<Char, Int> {
        return inputString.groupingBy { it }.eachCount()
    }

    fun hasOnlyDigits(inputString: String): Boolean {
        return inputString.all { it.isDigit() }
    }
}

fun main() {
    setOf("aardvark", "abcdefgabce", "0123", "-0123").forEach { input ->
        setOf(
            StringProblems::countDuplicateChars,
            StringProblems::countDuplicateCharsGroupingBy,
            StringProblems::firstNonRepeatingCharNestedLoop,
            StringProblems::firstNonRepeatingLinkedHashMap,
            StringProblems::hasOnlyDigits
        ).forEach {
            println("${it.name} -> $input: ${it(input)}")
        }
    }
}