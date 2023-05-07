import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import java.nio.charset.Charset
import java.util.UUID
import kotlin.math.absoluteValue

class CountMinSketch<T>(size: Int, numHashes: Int) {
    private val entitySize: Int
    private val data: Array<IntArray>
    private val hashers: List<HashFunction>

    init {
        entitySize = size
        hashers = hashers(numHashes)
        data = Array(hashers.size) { IntArray(size) }
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

    fun print() {
        data.forEachIndexed { index, value -> println("$index -> ${value.contentToString()}") }
    }

    fun hashes(item: T): List<Int> = computeHashes(item)

    private fun computeHashes(value: T): List<Int> {
        return hashers.map {
            it.hashString(value.toString(), Charset.defaultCharset()).asInt().absoluteValue % entitySize
        }.toList()
    }

    companion object {
        fun hashers(count: Int): List<HashFunction> {
            return listOf(
                Hashing.adler32(),
                Hashing.sha512(),
                Hashing.murmur3_128(),
                Hashing.sha384(),
                Hashing.farmHashFingerprint64(),
                Hashing.fingerprint2011(),
                Hashing.sha256(),
                Hashing.sipHash24(),
                Hashing.crc32c()
            ).take(count)
        }
    }
}

fun main() {
    val sketch = CountMinSketch<UUID>(size = 100, numHashes = 9)

    var uuid: UUID? = null
    for (i in 0..500) {
        uuid = UUID.randomUUID()
        sketch.put(uuid)
    }

    sketch.print()

    println(uuid)
    println(sketch.estimate(uuid!!))

    sketch.remove(uuid)
    sketch.print()
    println(sketch.estimate(uuid))
    println(sketch.hashes(uuid))

    println(sketch.estimate(UUID.randomUUID()))
}