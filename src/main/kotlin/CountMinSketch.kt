import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import java.nio.charset.Charset
import kotlin.math.absoluteValue

class CountMinSketch<T>(size: Int, numHashes: Int) {
    private val entitySize: Int
    private val data: Array<IntArray>
    private val hashes: List<HashFunction>

    init {
        entitySize = size
        data = Array(numHashes) { IntArray(size) }
        hashes = hashes(numHashes)
    }

    fun estimate(item: T): Int {
        return computeHashes(item).mapIndexed { index, value -> data[index][value] }.min()
    }

    fun put(item: T) {
        computeHashes(item).forEachIndexed { index, value -> data[index][value] += 1 }
    }

    fun remove(item: T) {
        computeHashes(item).forEachIndexed { index, value -> data[index][value] -= 1 }
    }

    private fun computeHashes(value: T): List<Int> {
        return hashes.map {
            it.hashString(value.toString(), Charset.defaultCharset()).asInt().absoluteValue % entitySize
        }.toList()
    }

    companion object {
        fun hashes(count: Int): List<HashFunction> {
            return listOf(
                Hashing.sha256(),
                Hashing.sha384(),
                Hashing.sha512(),
                Hashing.adler32(),
                Hashing.sipHash24(),
                Hashing.farmHashFingerprint64(),
                Hashing.murmur3_128(),
                Hashing.crc32c(),
                Hashing.fingerprint2011()
            ).take(count)
        }
    }
}

fun main() {
    val sketch = CountMinSketch<Record>(size = 10, numHashes = 5)
    val records = listOf(Record(120.0, 30.0), Record(100.0, 20.0), Record(60.0, 10.0))

    records.forEach { sketch.put(it) }

    println(sketch.estimate(Record(120.0, 30.0)))
    sketch.remove(Record(120.0, 30.0))
    println(sketch.estimate(Record(120.0, 30.0)))
}