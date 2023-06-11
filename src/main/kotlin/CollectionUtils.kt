object CollectionUtils {

    fun <T> nextGreaterElement(array: Array<T>, from: Int, comparator: Comparator<T>): T? {
        if (from > array.size - 1) {
            return null
        }

        val value = array[from]
        for (i in from + 1 until array.size) {
            if (comparator.compare(array[i], value) > 0) {
                return array[i]
            }
        }
        return null
    }

    fun <T> findNextGreaterElements(array: Array<T>, comparator: Comparator<T>): List<String> {

        val elements = mutableListOf<String>()

        for (i in 0 until array.size - 1) {
            var next: T? = null
            var nextIndex = -1

            for (j in i + 1 until array.size) {
                if (comparator.compare(array[j], array[i]) > 0) {
                    next = array[j]
                    nextIndex = j
                    break
                }
            }

            elements.add("[$i]:${array[i]} < [$nextIndex]:$next")
        }

        return elements
    }

    fun <T> findNextGreaterElementsStack(array: Array<T>, comparator: Comparator<T>): List<String> {

        val elements = mutableListOf<String>()
        val stack: ArrayDeque<T> = ArrayDeque()

        stack.push(array[0])

        for (i in 1 until array.size) {
            val nextItem = array[i]

            if (!stack.isEmpty()) {
                var item = stack.pop()!!

                while (comparator.compare(item, nextItem) < 0) {
                    elements.add("$item < $nextItem")

                    if (stack.isEmpty()) {
                        break
                    }

                    item = stack.pop()!!
                }

                if (comparator.compare(item, nextItem) > 0) {
                    stack.push(item)
                }
            }

            stack.push(nextItem)
        }

        while (!stack.isEmpty()) {
            elements.add("${stack.pop()} < -1")
        }

        return elements
    }
}
