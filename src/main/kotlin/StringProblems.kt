import java.util.stream.IntStream

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

    fun charCountUsingForEach(input: String?): Map<Char, Int> {
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

    fun charCount(inputString: String): Map<Char, Int> = inputString.groupingBy { it }.eachCount()

    fun hasOnlyDigits(inputString: String): Boolean = inputString.all { it.isDigit() }

    fun reverseWord(inputString: String): String = inputString.reversed()

    fun reverseWords(inputString: String): List<String> = inputString.split(" ").map { reverseWord(it) }

    fun vowelsAndConsonants(inputString: String): Pair<Long, Long> {
        val lower = inputString.lowercase()

        val numVowels = lower.chars().mapToObj(Int::toChar).filter(Char::isVowel).count()
        val numConsonants = lower.chars().mapToObj(Int::toChar).filter(Char::isConsonant).count()

        return numVowels to numConsonants
    }

    fun occurrences(inputString: String, character: Char): Int =
        inputString.length - inputString.replace(character.toString(), "").length

    fun join(delimiter: Char, vararg words: String): String = words.joinToString(separator = delimiter.toString())

    fun isPalindrome(inputString: String): Boolean {
        val value = inputString.filterNot { it.isWhitespace() }
        return IntStream.range(0, value.length / 2).allMatch {
            value[it] == value[value.length - it - 1]
        }
    }

    fun sortByLength(chars: Array<String>) = chars.sortBy { it.length }

    fun mostFrequent(inputString: String): Pair<Char, Int> {
        return charCount(inputString).maxBy { it.value }.toPair()
    }

    fun isSubstring(inputString: String, possibleSub: String): Boolean = inputString.contains(possibleSub)

    fun longestCommonPrefix(inputs: List<String>): String? {
        if (inputs.isEmpty() || inputs[0].isEmpty()) {
            return null
        }

        val maxLength = inputs[0].length

        (0..maxLength).forEach { prefixLen ->
            val currentChar = inputs[0][prefixLen]

            (1 until inputs.size).forEach { i ->
                if (prefixLen >= inputs[i].length) {
                    return inputs[0].substring(0, prefixLen)
                }

                if (inputs[i][prefixLen] != currentChar) {
                    return inputs[0].substring(0, prefixLen)
                }
            }
        }

        return inputs[0][0].toString()
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
    setOf(
        "tacocat",
        "1 2 3 4",
        "12 23 34 45",
        "aardvark",
        "abcdefgabce",
        "0123",
        "-0123",
        "madam",
        "m a d a m "
    ).forEach { input ->
        setOf(
            StringProblems::reverseWord,
            StringProblems::reverseWords,
            StringProblems::charCountUsingForEach,
            StringProblems::charCount,
            StringProblems::firstNonRepeatingCharNestedLoop,
            StringProblems::firstNonRepeatingLinkedHashMap,
            StringProblems::hasOnlyDigits,
            StringProblems::vowelsAndConsonants,
            StringProblems::isPalindrome,
            StringProblems::mostFrequent
        ).forEach {
            println("${it.name} -> $input: ${it(input)}")
        }

        println("occurrences a: " + StringProblems.occurrences(input, 'a'))
        println("-".repeat(20))
    }

    println("joining: " + StringProblems.join('_', "A", "B", "C"))

    val arr = arrayOf("111234", "1113333333", "111234", "111abc", "11199999")
    println("sortBy before: ${arr.contentToString()}")
    StringProblems.sortByLength(arr)
    println("sortBy after: ${arr.contentToString()}")
    val longestCommonPrefix = StringProblems.longestCommonPrefix(arr.toList())
    println("Longest Common Prefix: ${longestCommonPrefix}")
}