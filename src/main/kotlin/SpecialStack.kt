import kotlin.collections.ArrayDeque
import kotlin.random.Random

data class SpecialStack<T : Comparable<T>> @JvmOverloads constructor(
        private val mainStack: ArrayDeque<T> = ArrayDeque(),
        private val minStack: ArrayDeque<T> = ArrayDeque()) {

    fun push(element: T) {
        mainStack.push(element)

        val currentMin: T
        if (!minStack.isEmpty()) {
            currentMin = minStack.pop()!!
            minStack.push(currentMin)
        } else {
            currentMin = element
        }

        val newMin = if (element > currentMin) currentMin else element
        minStack.push(newMin)
    }

    fun pop(): T? {
        if (mainStack.isEmpty() || minStack.isEmpty()) {
            return null
        }

        minStack.pop()
        return mainStack.pop()
    }

    val min: T
        get() = minStack.first()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = SpecialStack<Int>()
            val size = 10

            for (i in 0..10) {
                stack.push(Random.nextInt(0, 100))
            }
            println(stack)

            for (i in 0..size) {
                try {
                    println("Min: " + stack.min)
                } catch (e: NoSuchElementException) {
                    println("No min present: $e")
                }

                println("Value: " + stack.pop())
                println(stack)
            }
        }
    }
}
