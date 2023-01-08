import kotlin.math.ceil
import kotlin.math.sqrt

fun Int.isPrime() =
    this == 2 || (2..ceil(sqrt(this.toDouble())).toInt()).none { divisor -> this % divisor == 0 }

class Primes {
    fun nextPrime(num: Int) = generateSequence(num + 1) { it + 1 }.first(Int::isPrime)

    fun findNPrimes(count: Int) = generateSequence(2, ::nextPrime).take(count).toList()
}

fun main() {
    val primes = Primes()

    listOf(4, 9, 33, 1000, 1977, 1980, 2023, 2077, 2080, 4544, 90090909).forEach { println(primes.nextPrime(it)) }

    println(primes.findNPrimes(500))
}