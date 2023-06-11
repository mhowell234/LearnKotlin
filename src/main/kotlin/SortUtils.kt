import java.util.Random
import java.util.UUID

data class Data(val id: UUID, val userId: Int)

object SortUtils {

    // Bubble up values. Ensures per loop, the newest found large value will be placed correctly.
    fun <T> bubbleSort(array: Array<T>, comparator: Comparator<T>) {
        val length = array.size

        for (i in 0 until length - 1) {
            for (j in 0 until length - 1 - i) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    swap(array, j, j + 1)
                }
            }
        }
    }

    // Start with 2nd element, if before is smaller swap...
    fun <T> insertionSort(array: Array<T>, comparator: Comparator<in T>) {
        val length = array.size

        for (i in 1 until length) {
            val item = array[i]
            var j = i - 1;

            while (j >= 0 && comparator.compare(array[j], item) > 0) {
                array[j + 1] = array[j]
                j -= 1
            }
            array[j + 1] = item
        }
    }

    private fun <T> swap(array: Array<T>, i: Int, j: Int) {
        val tempValue = array[i]
        array[i] = array[j]
        array[j] = tempValue
    }
}
