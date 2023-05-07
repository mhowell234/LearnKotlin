import kotlin.math.max

object ZeroOneNapSack {
    fun napSack(records: List<Record>, totalCapacity: Double): Double {
        val sortedRecords = records.sortedBy { it.weight }
        val memoized = mutableMapOf<String, Double>()
        val results = napSackInternal(totalCapacity, sortedRecords, sortedRecords.size, memoized)
        println(memoized)
        return results
    }

    private fun napSackInternal(
        currentCapacity: Double,
        records: List<Record>,
        index: Int,
        memoized: MutableMap<String, Double>
    ): Double {

        // Base case
        if (currentCapacity.equalsDelta(0) || index == 0) {
            return 0.0
        }

        val key = "${currentCapacity}-${index}"
        if (memoized.containsKey(key)) {
            return memoized[key]!!
        }

        // If weight is larger than capacity, skip
        return if (records[index - 1].weight > currentCapacity) {
            memoized[key] = napSackInternal(currentCapacity, records, index - 1, memoized)
            return memoized[key]!!
        } else {
            memoized[key] = max(
                records[index - 1].profit + napSackInternal(
                    currentCapacity - records[index - 1].weight,
                    records,
                    index - 1,
                    memoized
                ),
                napSackInternal(currentCapacity, records, index - 1, memoized)
            )
            return memoized[key]!!
        }


    }
}

fun main() {
    val records = listOf(Record(120.0, 30.0), Record(100.0, 20.0), Record(60.0, 10.0))
    val totalWeight = 50.0
    println(ZeroOneNapSack.napSack(records, totalWeight))
}