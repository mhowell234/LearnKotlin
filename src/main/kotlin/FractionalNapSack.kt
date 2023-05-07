object FractionalNapSack {
    fun maxProfit(records: List<Record>, totalCapacity: Double): Double {

        // Sorted by profit-weight ratio
        val sortedRecords = records.sortedBy { it.profit / it.weight }.reversed()

        var profit = 0.0
        var capacityLeft = totalCapacity
        sortedRecords.forEach records@{
            if (capacityLeft >= it.weight) {
                profit += it.profit
                capacityLeft -= it.weight
            } else {
                val fraction = capacityLeft / it.weight
                profit += it.profit * fraction
                return@records
            }
        }
        return profit
    }
}

fun main() {
    val records = listOf(Record(100.0, 20.0), Record(60.0, 10.0), Record(120.0, 30.0))
    val totalWeight = 50.0
    println(FractionalNapSack.maxProfit(records, totalWeight))
}