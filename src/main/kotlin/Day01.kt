fun main() {
    val input = {}.javaClass.getResource("Day01Input").readText()
    val summedUpCalories = input
        .split("\n\n")
        .map { caloriesPerElf ->
            caloriesPerElf
                .lines()
                .sumOf { caloriesPerSnack -> caloriesPerSnack.toLong() }
        }
        .sortedDescending()

    println(summedUpCalories[0])
    println(summedUpCalories.subList(0, 3).sum())


//    val caloriesSummedUp = mutableListOf<Int>()
//    elvesCalories.forEach { elf ->
//        val elfCalories = elf.split("\n")
//        val calories = mutableListOf<Int>()
//        elfCalories.forEach { entry ->
//            calories.add(entry.toInt())
//        }
//        caloriesSummedUp.add(calories.sum())
//    }
//
//    println(caloriesSummedUp.max())
//
//    caloriesSummedUp.sortDescending()
//    println(caloriesSummedUp[0] + caloriesSummedUp[1] + caloriesSummedUp[2])
}

