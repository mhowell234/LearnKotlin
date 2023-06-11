import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertContentEquals

internal class SortUtilsTest {

    private lateinit var input: Array<Data>

    @Test
    fun `bubble sort - sorts correctly`() {
        givenInputData()
        whenBubbleSortByUserId()
        thenExpectedSortOrder()
    }

    @Test
    fun `insertion sort - sorts correctly`() {
        givenInputData()
        whenInsertionSortByUserId()
        thenExpectedSortOrder()
    }

    @Test
    fun `heap sort - sorts correctly`() {
        givenInputData()
        whenHeapSortByUserId()
        thenExpectedSortOrder()
    }

    @Test
    fun `counting sort - sorts correctly`() {
        val ints = arrayOf(5, 3, 10, 1, 45, 0, 7, -5, 100, 1)
        SortUtils.countingSort(ints)
        assertContentEquals(arrayOf(-5, 0, 1, 1, 3, 5, 7, 10, 45, 100), ints)
    }

    fun givenInputData() {
        input = arrayOf(
            Data(UUID.fromString("97e12f93-5470-475d-94cb-44d930558e33"), 99486),
            Data(UUID.fromString("974da460-0180-4f15-a503-d50675719bfa"), 23848),
            Data(UUID.fromString("68f7a2ff-e9c3-4c45-a8f2-46046ac0aeaf"), 91920),
            Data(UUID.fromString("5e8c8aca-91af-4883-a597-5e9412da69c9"), 66001),
            Data(UUID.fromString("902e5e01-c699-471e-b8da-1f1ff637ec1c"), 7489),
            Data(UUID.fromString("4913a305-6786-4328-ac98-5d845c9ecfcb"), 49751),
            Data(UUID.fromString("c08953c7-db7d-47b9-80b9-c61fff24aa0f"), 94215),
            Data(UUID.fromString("faf00f5e-d765-4182-98ed-056f95f626e5"), 29151),
            Data(UUID.fromString("ed20cd44-08b4-4318-9cde-bab0a71ea006"), 69926),
            Data(UUID.fromString("690b77da-d81d-4019-a593-355ae2626043"), 47256),
            Data(UUID.fromString("77ee36fa-fc24-4bc5-b252-bbc8a50bab6d"), 5970)
        )
    }

    fun whenBubbleSortByUserId() {
        SortUtils.bubbleSort(input, compareBy { it.userId })
    }

    fun whenInsertionSortByUserId() {
        SortUtils.insertionSort(input, compareBy { it.userId })
    }

    fun whenHeapSortByUserId() {
        SortUtils.heapSort(input, compareBy { it.userId })
    }

    fun thenExpectedSortOrder() {
        val expectedOrder = arrayOf(
            Data(UUID.fromString("77ee36fa-fc24-4bc5-b252-bbc8a50bab6d"), 5970),
            Data(UUID.fromString("902e5e01-c699-471e-b8da-1f1ff637ec1c"), 7489),
            Data(UUID.fromString("974da460-0180-4f15-a503-d50675719bfa"), 23848),
            Data(UUID.fromString("faf00f5e-d765-4182-98ed-056f95f626e5"), 29151),
            Data(UUID.fromString("690b77da-d81d-4019-a593-355ae2626043"), 47256),
            Data(UUID.fromString("4913a305-6786-4328-ac98-5d845c9ecfcb"), 49751),
            Data(UUID.fromString("5e8c8aca-91af-4883-a597-5e9412da69c9"), 66001),
            Data(UUID.fromString("ed20cd44-08b4-4318-9cde-bab0a71ea006"), 69926),
            Data(UUID.fromString("68f7a2ff-e9c3-4c45-a8f2-46046ac0aeaf"), 91920),
            Data(UUID.fromString("c08953c7-db7d-47b9-80b9-c61fff24aa0f"), 94215),
            Data(UUID.fromString("97e12f93-5470-475d-94cb-44d930558e33"), 99486)
        )

        assertContentEquals(expectedOrder, input)
    }
}
