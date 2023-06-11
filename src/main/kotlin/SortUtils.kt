import java.util.Random
import java.util.UUID

data class Data(val id: UUID, val userId: Int)

object SortUtils {

    // Bubble up values. Ensures per loop, the newest found largest value will be placed correctly.
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

    fun <T> swap(array: Array<T>, i: Int, j: Int) {
        val tempValue = array[i]
        array[i] = array[j]
        array[j] = tempValue
    }
}

fun main() {
    val random = Random()

    val items = (0..10).map { Data(UUID.randomUUID(), random.nextInt(100_000)) }.toTypedArray()

    println("Before: " + items.contentToString())
    SortUtilsJava.bubbleSort(items, Comparator.comparing(Data::userId))
    println("Bubble Sorted: " + items.contentToString())
}
