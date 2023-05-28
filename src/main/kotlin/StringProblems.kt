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

        input.chars().forEach {
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
}

fun main() {
    setOf("aardvark", "abcdefgabce").forEach { input ->
        setOf(
            StringProblems::countDuplicateChars,
            StringProblems::firstNonRepeatingCharNestedLoop,
            StringProblems::firstNonRepeatingLinkedHashMap
        ).forEach {
            println("${it.name} -> $input: ${it(input)}")
        }
    }
}