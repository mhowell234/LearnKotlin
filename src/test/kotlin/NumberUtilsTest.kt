import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NumberUtilsTest {

    @Test
    fun `double delta equals`() {
        assertTrue { 0.42.equalsDelta(0.42) }
    }

    @Test
    fun `factorial expected output`() {
        assertEquals(1, 1.factorial())
        assertEquals(2, 2.factorial())
        assertEquals(6, 3.factorial())
        assertEquals(24, 4.factorial())
        assertEquals(120, 5.factorial())
    }

    @Test
    fun `permutations expected output`() {
        assertEquals(120, 5.permutations(5))
        assertEquals(120, 5.permutations(4))
        assertEquals(60, 5.permutations(3))
        assertEquals(20, 5.permutations(2))
        assertEquals(5, 5.permutations(1))
    }

    @Test
    fun `combinations expected output`() {
        assertEquals(1, 5.combinations(5))
        assertEquals(5, 5.combinations(4))
        assertEquals(10, 5.combinations(3))
        assertEquals(10, 5.combinations(2))
        assertEquals(5, 5.combinations(1))
    }
}