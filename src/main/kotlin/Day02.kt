import RockPaperScissors.*

enum class RockPaperScissors(val charSet: Set<Char>, val points: Int) {
    ROCK(setOf('A', 'X'), 1),
    PAPER(setOf('B', 'Y'), 2),
    SCISSORS(setOf('C', 'Z'), 3);

    companion object {
        fun from(value: String): RockPaperScissors {
            return values().singleOrNull { it.charSet.contains(value.first()) } ?: error("$value unknown")
        }
    }
}

private fun String.toRockPaperScissors(task: String): Pair<RockPaperScissors, RockPaperScissors> =
    if (task == "1") {
        this.split(" ").let { split ->
            RockPaperScissors.from(split.first()) to RockPaperScissors.from(split.last())
        }
    } else {
        this.split(" ").let { split ->
            val elvesFigure = RockPaperScissors.from(split.first())
            elvesFigure to chooseMyHand(elvesFigure, split.last())
        }
    }

private fun chooseMyHand(elvesFigure: RockPaperScissors, last: String) =
    when (last to elvesFigure) {
        "X" to SCISSORS -> PAPER
        "X" to PAPER -> ROCK
        "X" to ROCK -> SCISSORS
        "Z" to SCISSORS -> ROCK
        "Z" to PAPER -> SCISSORS
        "Z" to ROCK -> PAPER

        else -> elvesFigure
    }

fun playRound(round: Pair<RockPaperScissors, RockPaperScissors>): Int {
    if (round.first == round.second) {
        return round.second.points + 3
    }

    return round.second.points + when (round) {
        ROCK to PAPER, PAPER to SCISSORS, SCISSORS to ROCK -> 6
        else -> 0
    }
}

fun play(input: List<String>, task: String = ""): Int = input.sumOf { playRound(it.toRockPaperScissors(task)) }

fun main() {
    val input = readInput("Day02Input")
    println(play(input.lines(),"1"))
    println(play(input.lines()))
}