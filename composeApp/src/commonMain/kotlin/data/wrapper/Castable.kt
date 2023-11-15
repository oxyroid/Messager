package data.wrapper

fun castable(
    text: String,
    builder: MutableList<Pair<*, *>>.() -> Unit
): CastableException {
    return CastableException(text, buildList(builder))
}

class CastableException internal constructor(
    private val text: String,
    private val metadata: List<Pair<*, *>> = emptyList()
) : RuntimeException() {
    override fun toString(): String = buildString {
        appendLine(text)
        appendLine()
        metadata.forEach { line -> appendLine(line.first.toString() + ": " + line.second.toString()) }
    }
}

interface Castable {
    fun cast(): CastableException
}
