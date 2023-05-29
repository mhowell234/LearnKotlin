import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringProblemsTest {

    @Test
    fun `find first non repeating char using nested loop`() {
        val cases = listOf(
            "tacocat" to 'o',
            "1 2 3 4" to '1',
            "152 23 34 4715" to '7',
            "aardvark" to 'd',
            "madam" to 'd',
            "m a d a m" to 'd'
        )

        cases.forEach {
            assertEquals(it.second, StringProblems.firstNonRepeatingCharNestedLoop(it.first))
        }
    }

    @Test
    fun `find first non repeating char using linked hash map`() {
        val cases = listOf(
            "tacocat" to 'o',
            "1 2 3 4" to '1',
            "152 23 34 4715" to '7',
            "aardvark" to 'd',
            "madam" to 'd',
            "m a d a m" to 'd'
        )

        cases.forEach {
            assertEquals(it.second, StringProblems.firstNonRepeatingLinkedHashMap(it.first))
        }
    }

    @Test
    fun `char count using groupingBy`() {
        val cases = listOf(
            "tacocat" to mapOf('t' to 2, 'a' to 2, 'c' to 2, 'o' to 1),
            "1 2 3 4" to mapOf(' ' to 3, '1' to 1, '2' to 1, '3' to 1, '4' to 1),
            "152 23 34 4715" to mapOf(' ' to 3, '1' to 2, '2' to 2, '3' to 2, '4' to 2, '5' to 2, '7' to 1),
            "aardvark" to mapOf('a' to 3, 'r' to 2, 'd' to 1, 'v' to 1, 'k' to 1),
            "madam" to mapOf('m' to 2, 'a' to 2, 'd' to 1),
            "m a d a m" to mapOf(' ' to 4, 'm' to 2, 'a' to 2, 'd' to 1)
        )

        cases.forEach {
            assertEquals(it.second, StringProblems.charCount(it.first))
        }
    }

    @Test
    fun `reverse word`() {
        val cases = listOf(
            "tacocat" to "tacocat",
            "1 2 3 4" to "4 3 2 1",
            "152 23 34 4715" to "5174 43 32 251",
            "aardvark" to "kravdraa",
            "madam" to "madam",
            "m a d a m" to "m a d a m"
        )

        cases.forEach {
            assertEquals(it.second, StringProblems.reverseWord(it.first))
        }
    }

    @Test
    fun `vowels and consonants`() {

        val cases = listOf(
            Pair("tacocat", 3L to 4L),
            Pair("1 2 3 4", 0L to 0L),
            Pair("152 23 34 4715", 0L to 0L),
            Pair("aardvark", 3L to 5L),
            Pair("madam", 2L to 3L),
            Pair("m a d a m", 2L to 3L)
        )

        cases.forEach {
            assertEquals(it.second, StringProblems.vowelsAndConsonants(it.first))
        }
    }
}