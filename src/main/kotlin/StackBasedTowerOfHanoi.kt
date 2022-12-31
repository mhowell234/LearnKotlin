fun <T> ArrayDeque<T>.push(element: T) = addFirst(element)
fun <T> ArrayDeque<T>.pop() = removeFirstOrNull()

class StackBasedTowerOfHanoi(private val stacks: Map<StackType, ArrayDeque<Int>>) {
    fun solve(num: Int) {
        solveInternal(num, StackType.SRC, StackType.DEST, StackType.AUX)
    }

    private fun solveInternal(
        num: Int,
        source: StackType,
        destination: StackType,
        auxiliary: StackType
    ) {
        if (num > 0) {
            solveInternal(num - 1, source, auxiliary, destination)

            val value = stacks[source]?.pop() as Int
            stacks[destination]?.push(value)

            println("Move disk $value from $source to $destination -> $stacks")

            solveInternal(num - 1, auxiliary, destination, source)
        }
    }

    enum class StackType {
        SRC, DEST, AUX
    }

    companion object {
        private fun initStacks(size: Int): Map<StackType, ArrayDeque<Int>> {
            val stacks = createStacks(size)

            val sourceStack = stacks[StackType.SRC]
            for (i in size downTo 1) sourceStack?.push(i)

            return stacks
        }

        private fun createStacks(maxSize: Int): Map<StackType, ArrayDeque<Int>> {
            return StackType.values().associateWith { ArrayDeque(maxSize) }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val size = 4
            val towerOfHanoi = StackBasedTowerOfHanoi(initStacks(size))
            println("Start: " + towerOfHanoi.stacks)
            towerOfHanoi.solve(size)
            println("End: " + towerOfHanoi.stacks)
        }
    }
}
