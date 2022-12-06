/**
 *
 * Input Stacks:
 *
 *            [Q] [B]         [H]
 *        [F] [W] [D] [Q]     [S]
 *        [D] [C] [N] [S] [G] [F]
 *        [R] [D] [L] [C] [N] [Q]     [R]
 *    [V] [W] [L] [M] [P] [S] [M]     [M]
 *    [J] [B] [F] [P] [B] [B] [P] [F] [F]
 *    [B] [V] [G] [J] [N] [D] [B] [L] [V]
 *    [D] [P] [R] [W] [H] [R] [Z] [W] [S]
 *     1   2   3   4   5   6   7   8   9
 */
fun main() {
    var stack1 = ArrayDeque(listOf("V", "J", "B", "D"))
    var stack2 = ArrayDeque(listOf("F", "D", "R", "W", "B", "V", "P"))
    var stack3 = ArrayDeque(listOf("Q", "W", "C", "D", "L", "F", "G", "R"))
    var stack4 = ArrayDeque(listOf("B", "D", "N", "L", "M", "P", "J", "W"))
    var stack5 = ArrayDeque(listOf("Q", "S", "C", "P", "B", "N", "H"))
    var stack6 = ArrayDeque(listOf("G", "N", "S", "B", "D", "R"))
    var stack7 = ArrayDeque(listOf("H", "S", "F", "Q", "M", "P", "B", "Z"))
    var stack8 = ArrayDeque(listOf("F", "L", "W"))
    var stack9 = ArrayDeque(listOf("R", "M", "F", "V", "S"))

    fun getStackFromInt(number: Int) =
        when (number) {
            1 -> stack1
            2 -> stack2
            3 -> stack3
            4 -> stack4
            5 -> stack5
            6 -> stack6
            7 -> stack7
            8 -> stack8
            9 -> stack9
            else -> throw error("stacks")
        }

    val regex = """move (?<quantity>\d+) from (?<from>\d+) to (?<to>\d+)""".toRegex()

    val instructions = readInput("Day05Input").lines()
    for (instruction in instructions) {
        val quantity = regex.find(instruction)?.groups?.get("quantity")?.value?.toInt() ?: throw error("quantity")
        val from = regex.find(instruction)?.groups?.get("from")?.value?.toInt() ?: throw error("from")
        val to = regex.find(instruction)?.groups?.get("to")?.value?.toInt() ?: throw error("to")

//        Task 1
//        repeat(quantity) {
//            val crate = getStackFromInt(from).removeFirst()
//            getStackFromInt(to).addFirst(crate)
//        }

        // Task 2
        val crates = mutableListOf<String>()
        repeat(quantity) {
            crates.add(0, getStackFromInt(from).removeFirst())
        }
        for (crate in crates) {
            getStackFromInt(to).addFirst(crate)
        }
    }

    println("${stack1.firstOrNull()}${stack2.firstOrNull()}${stack3.firstOrNull()}${stack4.firstOrNull()}${stack5.firstOrNull()}${stack6.firstOrNull()}${stack7.firstOrNull()}${stack8.firstOrNull()}${stack9.firstOrNull()}")
}
