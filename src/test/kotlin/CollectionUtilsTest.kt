import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CollectionUtilsTest {

    @Test
    fun `next greater elements are found`() {
        val input = arrayOf(3, 4, 2, 8, 1, -1, 111, 4, 8)

        assertEquals(4, CollectionUtils.nextGreaterElement(input, 0, compareBy { it }))
        assertEquals(8, CollectionUtils.nextGreaterElement(input, 1, compareBy { it }))
        assertEquals(8, CollectionUtils.nextGreaterElement(input, 2, compareBy { it }))
        assertEquals(111, CollectionUtils.nextGreaterElement(input, 3, compareBy { it }))
        assertEquals(111, CollectionUtils.nextGreaterElement(input, 4, compareBy { it }))
        assertEquals(111, CollectionUtils.nextGreaterElement(input, 5, compareBy { it }))
        assertEquals(null, CollectionUtils.nextGreaterElement(input, 6, compareBy { it }))
        assertEquals(8, CollectionUtils.nextGreaterElement(input, 7, compareBy { it }))
        assertEquals(null, CollectionUtils.nextGreaterElement(input, 8, compareBy { it }))
    }

    @Test
    fun `all next greater elements`() {
        val input = arrayOf(3, 4, 2, 8, 1, -1, 111, 4, 8)

        assertEquals(
            listOf(
                "[0]:3 < [1]:4",
                "[1]:4 < [3]:8",
                "[2]:2 < [3]:8",
                "[3]:8 < [6]:111",
                "[4]:1 < [6]:111",
                "[5]:-1 < [6]:111",
                "[6]:111 < [-1]:null",
                "[7]:4 < [8]:8"
            ), CollectionUtils.findNextGreaterElements(input, compareBy { it })
        )
    }

    @Test
    fun `all next greater elements stack`() {
        val input = arrayOf(3, 4, 2, 8, 1, -1, 111, 4, 8)

        assertEquals(
            listOf("3 < 4", "2 < 8", "4 < 8", "-1 < 111", "1 < 111", "8 < 111", "4 < 8", "8 < -1", "111 < -1"),
            CollectionUtils.findNextGreaterElementsStack(input, compareBy { it })
        )
    }
}
