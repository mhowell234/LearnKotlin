import kotlin.math.abs

fun Double.equalsDelta(other: Number) = abs(this / other.toDouble() - 1) < 0.000001

fun Int.factorial(): Int {
    return if (this <= 1) 1 else this * (this - 1).factorial()
}

fun Int.permutations(choose: Int): Int {
    return factorial() / (this - choose).factorial()
}

fun Int.combinations(choose: Int): Int {
    return permutations(choose) / choose.factorial()
}
