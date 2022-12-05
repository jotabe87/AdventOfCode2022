fun main() {
    // Task 1
    val allRucksacks = readInput("Day03Input").lines()
    allRucksacks.map { rucksack ->
        rucksack.slice(0 until rucksack.length / 2).toList().distinct().firstNotNullOf { item ->
            if (rucksack.slice(rucksack.length / 2 until rucksack.length).contains(item)) item else null
        }
    }.map(::getPriority).sum().also { println(it) }


    // Task 2
    val priorities = mutableListOf<Int>()
    for ((index, elf1) in allRucksacks.withIndex()) {
        if (index % 3 != 0) continue

        val elf2 = allRucksacks[index + 1].toList().distinct()
        val elf3 = allRucksacks[index + 2].toList().distinct()

        elf1.toList().distinct().firstNotNullOf {
            if (elf2.contains(it) && elf3.contains(it)) it else null
        }.let {
            priorities.add(getPriority(it))
        }
    }
    println(priorities.sum())

    // Task 2 more functional
    allRucksacks.mapIndexedNotNull() { index, elf1 ->
        if (index % 3 != 0) {
            null
        } else {
            val elf2 = allRucksacks[index + 1].toList().distinct()
            val elf3 = allRucksacks[index + 2].toList().distinct()

            elf1.toList().distinct().firstNotNullOf { if (elf2.contains(it) && elf3.contains(it)) it else null }
        }
    }.map(::getPriority).sum().also { println(it) }
}

private fun getPriority(c: Char): Int = when (c) {
    in 'a'..'z' -> c - 'a' + 1
    in 'A'..'Z' -> c - 'A' + 27
    else -> throw error("char not in any range")
}