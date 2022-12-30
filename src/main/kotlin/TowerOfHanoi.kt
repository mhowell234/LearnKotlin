object TowerOfHanoi {

    @JvmStatic
    fun towerOfHanoi(iteration: Int, num: Int, fromRod: Char, toRod: Char, auxRod: Char) {
        if (num == 0) {
            return
        }
        if (num == 1) {
            printMessage(iteration, num, fromRod, toRod)
            return
        }
        towerOfHanoi(iteration + 1, num - 1, fromRod, auxRod, toRod)
        printMessage(iteration, num, fromRod, toRod)
        towerOfHanoi(iteration + 1, num - 1, auxRod, toRod, fromRod)
    }

    private fun printMessage(iteration: Int, num: Int, fromRod: Char, toRod: Char) {
        println("$iteration: Moving disk $num from $fromRod to $toRod")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        towerOfHanoi(0, 4, 'A', 'B', 'C')
    }
}
