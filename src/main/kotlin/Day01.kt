fun main() {
    val summedUpCalories = readInput("Day01Input")
        .split("\n\n")
        .map { caloriesPerElf ->
            caloriesPerElf
                .lines()
                .sumOf { caloriesPerSnack -> caloriesPerSnack.toLong() }
        }
        .sortedDescending()

    println(summedUpCalories[0])
    println(summedUpCalories.subList(0, 3).sum())
}

