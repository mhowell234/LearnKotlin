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

    fun reverseWord(inputString: String): String {
        return inputString.reversed()
    }

    fun reverseWords(inputString: String): List<String> {
        return inputString.split(" ").map { reverseWord(it) }
    }

    fun vowelsAndConsonants(inputString: String): Pair<Long, Long> {
        val lower = inputString.lowercase()

        val numVowels = lower.chars().mapToObj(Int::toChar).filter(Char::isVowel).count()
        val numConsonants = lower.chars().mapToObj(Int::toChar).filter(Char::isConsonant).count()

        return numVowels to numConsonants
    }

    fun occurrences(inputString: String, character: Char): Int {
        return inputString.length - inputString.replace(character.toString(), "").length
    }

    fun join(delimiter: Char, vararg words: String): String {
        return words.joinToString(separator = delimiter.toString())
    }
}

fun Char.isVowel(): Boolean {
    return setOf("a", "e", "i", "o", "u").contains(this.lowercase())
}

fun Char.isConsonant(): Boolean {
    return this in 'a'..'z' && !isVowel()
}

fun main() {
    println("-".repeat(20))
    setOf("1 2 3 4", "12 23 34 45", "aardvark", "abcdefgabce", "0123", "-0123").forEach { input ->
        setOf(
            StringProblems::reverseWord,
            StringProblems::reverseWords,
            StringProblems::countDuplicateChars,
            StringProblems::countDuplicateCharsGroupingBy,
            StringProblems::firstNonRepeatingCharNestedLoop,
            StringProblems::firstNonRepeatingLinkedHashMap,
            StringProblems::hasOnlyDigits,
            StringProblems::vowelsAndConsonants
        ).forEach {
            println("${it.name} -> $input: ${it(input)}")
        }

        println("occurrences: " + StringProblems.occurrences(input, 'a'))
        println("joining: " + StringProblems.join('_', "A", "B", "C"))
        println("-".repeat(20))
    }
}