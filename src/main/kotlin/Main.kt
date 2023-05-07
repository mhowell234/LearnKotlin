import kotlin.math.max

fun main() {

    val fruit = arrayOf("Apple", "Banana", "Cherry", "Blueberry", "Pomegranate")
    val index = arrayOf(1, 3, 4, 2)

    var x = 0
    while (x < index.size) {
        println("Fruit = ${fruit[index[x]]}")
        x += 1
    }

    index.forEach { println("Fruit = ${fruit[it]}") }

    println(max(4, 9))
    println(max(10, 2))

    println(getGameChoice(arrayOf("Matt", "Marnie", "TyTy", "Linda", "Preston", "Charlie", "Lexi")))
}

fun getGameChoice(optionsParam: Array<String>) = optionsParam[(Math.random() * optionsParam.size).toInt()]
