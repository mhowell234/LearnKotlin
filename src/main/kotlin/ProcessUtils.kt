import java.util.Locale


object ProcessUtils {
    fun processNullableString(str: String?) =

        str?.let { it ->
            when {
                it.isEmpty() -> "Empty"
                it.isBlank() -> "Blank"
                else -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }
        } ?: "Null"
}

fun main() {
    listOf("abc", "", null, " ", "fred", "George", "riveR", "dOUBT").map(ProcessUtils::processNullableString).forEach(::println)
}