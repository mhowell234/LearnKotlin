data class StackUsingQueue<T> @JvmOverloads constructor(
        private var mainQueue: MutableList<T> = mutableListOf(),
        private var auxQueue: MutableList<T> = mutableListOf()) {

    fun pop(): T? {

        while (mainQueue.size != 1) {
            println("1: $mainQueue -- $auxQueue")
            auxQueue.add(mainQueue[0])
            println("2: $mainQueue -- $auxQueue")
            mainQueue.removeAt(0)
            println("3: $mainQueue -- $auxQueue")
        }

        val value = mainQueue[0]
        mainQueue.removeAt(0)

        mainQueue = auxQueue.apply { auxQueue = mainQueue }

        return value
    }

    fun push(element: T) {
        mainQueue.add(element)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = StackUsingQueue<String>()

            val items = listOf("A", "B", "C")
            items.forEach { stack.push(it) }

            println(stack)
            println(stack.pop())
            println(stack)
        }
    }
}
