package fr.deroffal.aoc_2022

fun main() {
    val input = getFileContent("day01.txt")
    val day01 = Day01(input)

    println(
        """
        part 1 : ${day01.part1()}
        part 2 : ${day01.part2()}
    """.trimIndent()
    )
}


class Day01(private val input: String, private val lineSeparator: String = System.lineSeparator()) {

    fun part1() = getCaloriesByElf()
        .maxOf { sumCalories(it) }

    fun part2() = getCaloriesByElf()
        .map { sumCalories(it) }
        .sortedDescending().take(3).sum()

    private fun getCaloriesByElf() = input.split("${lineSeparator}${lineSeparator}")

    private fun sumCalories(calories: String) = calories.split(lineSeparator).sumOf { it.toInt() }

}



