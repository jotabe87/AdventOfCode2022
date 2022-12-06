fun main() {
    val campSections = readInput("Day04Input").lines()

    // Task 1
    campSections.map { sections ->
        val ranges = sections.split(",")
        val rangeElf1 = IntRange(ranges.first().split("-").first().toInt(), ranges.first().split("-").last().toInt())
        val rangeElf2 = IntRange(ranges.last().split("-").first().toInt(), ranges.last().split("-").last().toInt())

        when {
            rangeElf1.contains(rangeElf2.first) && rangeElf1.contains(rangeElf2.last) -> 1
            rangeElf2.contains(rangeElf1.first) && rangeElf2.contains(rangeElf1.last) -> 1
            else -> 0
        }
    }.sum().also { println(it) }

    // Task 2
    campSections.map { sections ->
        val ranges = sections.split(",")
        val rangeElf1 = IntRange(ranges.first().split("-").first().toInt(), ranges.first().split("-").last().toInt()).toList()
        val rangeElf2 = IntRange(ranges.last().split("-").first().toInt(), ranges.last().split("-").last().toInt()).toList()


        var contained = 0
        rangeElf1.forEach {
            if (rangeElf2.contains(it)) {
                contained = 1
            }
        }

        if (contained == 0) {
            rangeElf2.forEach {
                if (rangeElf1.contains(it)) {
                    contained = 1
                }
            }
        }

        contained
    }.sum().also { println(it) }
}