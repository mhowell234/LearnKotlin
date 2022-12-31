data class QueueAsStack<T> @JvmOverloads constructor(
        private val mainQueue: ArrayDeque<T> = ArrayDeque(),
        private val auxQueue: ArrayDeque<T> = ArrayDeque()) {

    fun add(element: T) {
        mainQueue.push(element)
    }

    fun get(): T? {
        if (auxQueue.isEmpty()) {
            mainQueue.forEach { auxQueue.push(it) }
            mainQueue.clear()
        }

        return auxQueue.pop()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = QueueAsStack<String>()

            val items = listOf("A", "B", "C")
            items.forEach { queue.add(it) }

            println(queue)
            for (i in items.indices) {
                println(queue.get())
                listOf("D", "E", "F").forEach { queue.add(it) }
                println(queue)
            }

            println(queue.get())
            println(queue)
        }
    }
}
