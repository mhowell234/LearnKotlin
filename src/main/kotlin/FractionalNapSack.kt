object FractionalNapSack {
    fun maxProfit(weightedRecords: List<WeightedRecord>, totalCapacity: Double): Double {

        // Sorted by profit-weight ratio
        val sortedRecords = weightedRecords.sortedBy { it.profit / it.weight }.reversed()

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
    val records = listOf(
        WeightedRecordImpl(message = "1", 100.0, 20.0),
        WeightedRecordImpl(message = "2", 60.0, 10.0),
        WeightedRecordImpl(message = "3", 120.0, 30.0)
    )
    val totalWeight = 50.0
    println(FractionalNapSack.maxProfit(records, totalWeight))
}