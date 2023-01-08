data class QueueUsingStack<T> @JvmOverloads constructor(
        private val mainStack: ArrayDeque<T> = ArrayDeque(),
        private val auxStack: ArrayDeque<T> = ArrayDeque()) {

    fun add(element: T) {
        mainStack.push(element)
    }

    fun get(): T? {
        if (auxStack.isEmpty()) {
            mainStack.forEach { auxStack.push(it) }
            mainStack.clear()
        }

        return auxStack.pop()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = QueueUsingStack<String>()

            val items = listOf("A", "B", "C")
            items.forEach(queue::add)

            println(queue)
            for (i in items.indices) {
                println(queue.get())
                listOf("D", "E", "F").forEach(queue::add)
                println(queue)
            }

            println(queue.get())
            println(queue)
        }
    }
}
