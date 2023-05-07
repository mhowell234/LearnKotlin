import kotlin.math.abs

fun Double.equalsDelta(other: Number) = abs(this/other.toDouble() - 1) < 0.000001