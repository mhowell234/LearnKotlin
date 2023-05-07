import kotlin.math.max

object ZeroOneNapSack {
    fun napSack(weightedRecords: List<WeightedRecord>, totalCapacity: Double): Double {
        val sortedRecords = weightedRecords.sortedBy { it.weight }
        val memoized = mutableMapOf<String, Double>()
        val results = napSackInternal(totalCapacity, sortedRecords, sortedRecords.size, memoized)
        println(memoized)
        return results
    }

    private fun napSackInternal(
        currentCapacity: Double,
        weightedRecords: List<WeightedRecord>,
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
        return if (weightedRecords[index - 1].weight > currentCapacity) {
            memoized[key] = napSackInternal(currentCapacity, weightedRecords, index - 1, memoized)
            return memoized[key]!!
        } else {
            memoized[key] = max(
                weightedRecords[index - 1].profit + napSackInternal(
                    currentCapacity - weightedRecords[index - 1].weight,
                    weightedRecords,
                    index - 1,
                    memoized
                ),
                napSackInternal(currentCapacity, weightedRecords, index - 1, memoized)
            )
            return memoized[key]!!
        }


    }
}

fun main() {
    val records =
        listOf(
            WeightedRecordImpl("1", 120.0, 30.0),
            WeightedRecordImpl("2", 100.0, 20.0),
            WeightedRecordImpl("3", 60.0, 10.0)
        )
    val totalWeight = 50.0
    println(ZeroOneNapSack.napSack(records, totalWeight))
}