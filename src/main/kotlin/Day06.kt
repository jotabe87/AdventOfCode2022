fun main() {

    val input = readInput("Day06Input")
    val buffer = ArrayDeque<Char>()
    val markers = mutableListOf<Int>()

    input.forEachIndexed { i, c ->
        buffer.add(c)
        if (buffer.size == 14) {
            if (buffer.distinct().size == 14) {
                markers.add(i + 1)
            }
            buffer.removeFirst()
        }
    }

    println(markers.first())

}
