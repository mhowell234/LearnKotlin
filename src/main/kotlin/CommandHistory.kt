import kotlin.random.Random

enum class EventType {
    PUSH,
    POP
}

object StringUtils {
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun randomString(length: Int) = (1..length)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}

data class Event<T>(val type: EventType, val data: T)

data class CommandHistory<T : Any>(
    private val content: ArrayDeque<T> = ArrayDeque(),
    private val undoStack: ArrayDeque<Event<T>> = ArrayDeque(),
    private val redoStack: ArrayDeque<Event<T>> = ArrayDeque()
) {

    fun push(item: T) {
        content.push(item)
        undoStack.push(Event(EventType.PUSH, item))
        redoStack.clear()
    }

    fun pop(): T? {
        val item = content.pop()
        item?.let {
            undoStack.push(Event(EventType.POP, it))
            redoStack.clear()
        }
        return item
    }

    fun undo() {
        check(canUndo())

        undoStack.pop()?.let {
            when (it.type) {
                EventType.PUSH -> {
                    content.pop()?.let { item ->
                        redoStack.push(Event(EventType.PUSH, item))
                    }
                }

                EventType.POP -> {
                    content.push(it.data)
                    redoStack.push(Event(EventType.POP, it.data))
                }
            }
        }
    }

    fun redo() {
        check(canRedo())

        redoStack.pop()?.let {
            when (it.type) {
                EventType.PUSH -> {
                    content.push(it.data)
                    undoStack.push(Event(EventType.PUSH, it.data))
                }

                EventType.POP -> {
                    content.pop()?.let { item ->
                        undoStack.push(Event(EventType.POP, item))
                    }
                }
            }
        }
    }

    private fun canUndo(): Boolean {
        return !undoStack.isEmpty()
    }

    private fun canRedo(): Boolean {
        return !redoStack.isEmpty()
    }
}

fun main() {
    val history = CommandHistory<String>()
    for (i in 1..120) {
        history.push(StringUtils.randomString(2))
        if (i % 3 == 0) history.pop()
    }

    for (i in 1..50) {
        if (i % 3 == 0) history.redo() else history.undo()
        println(history)
    }
}