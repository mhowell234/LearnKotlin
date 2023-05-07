interface WeightedRecord {
    val profit: Double
    val weight: Double
}


data class WeightedRecordImpl(val message: String, override val profit: Double, override val weight: Double) :
    WeightedRecord
