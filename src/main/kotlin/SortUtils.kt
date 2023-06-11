import java.util.UUID

data class Data(val id: UUID, val userId: Int)

object SortUtils {

    // Bubble up values. Ensures per loop, the newest found large value will be placed correctly.
    fun <T> bubbleSort(array: Array<T>, comparator: Comparator<in T>) {
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
            var j = i - 1

            while (j >= 0 && comparator.compare(array[j], item) > 0) {
                array[j + 1] = array[j]
                j -= 1
            }
            array[j + 1] = item
        }
    }

    fun countingSort(array: Array<Int>) {
        val (min, max) = array.minMax()
        val size = max - min + 1
        val countingArray = IntArray(size)

        array.forEach { countingArray[it - min]++ }

        var sortedIndex = 0

        countingArray.indices.forEach {
            while (countingArray[it] > 0) {
                array[sortedIndex++] = it + min
                countingArray[it]--
            }
        }
    }

    fun <T> heapSort(array: Array<T>, comparator: Comparator<in T>) {
        val length = array.size
        buildHeap(array, length, comparator)

        for (n in length downTo 2) {
            swap(array, 0, n - 1)
            heapify(array, n - 1, 0, comparator)
        }
    }

    private fun <T> buildHeap(array: Array<T>, n: Int, comparator: Comparator<in T>) {
        for (i in array.size / 2 downTo 0) {
            heapify(array, n, i, comparator)
        }
    }

    private fun <T> heapify(array: Array<T>, n: Int, i: Int, comparator: Comparator<in T>) {
        val left = i * 2 + 1
        val right = i * 2 + 2

        // Find index of child with larger value
        var greater = if (left < n && comparator.compare(array[left], array[i]) > 0) { left } else { i }
        if (right < n && comparator.compare(array[right], array[greater]) > 0) {
            greater = right
        }

        // Change needed
        if (greater != i) {
            swap(array, i, greater)
            heapify(array, n, greater, comparator)
        }
    }

    private fun <T> swap(array: Array<T>, i: Int, j: Int) {
        val tempValue = array[i]
        array[i] = array[j]
        array[j] = tempValue
    }

    private fun Array<Int>.minMax(): Pair<Int, Int> = min() to max()
}
