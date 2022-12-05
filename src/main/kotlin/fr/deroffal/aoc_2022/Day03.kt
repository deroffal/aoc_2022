package fr.deroffal.aoc_2022

fun main() {
    val input = getFileContentAsList("day03.txt").takeWhile { it.isNotEmpty() }
    val day03 = Day03(input)

    println(
        """
        part 1 : ${day03.part1()}
        part 2 : ${day03.part2()}
    """.trimIndent()
    )
}

class Day03(private val rucksacks: List<String>) {

    fun part1() = rucksacks.map { getItemsPriority(it) }
        .sumOf { it[0].toSet().intersect(it[1].toSet()).first() }

    fun part2() = rucksacks.chunked(3)
        .sumOf { groups ->
            val priorities = groups.map { rucksack -> rucksack.map { it.getPriority() }.toSet() }
            priorities[0].intersect(priorities[1].intersect(priorities[2])).first()
        }

    private fun getItemsPriority(rucksack: String) = rucksack.toCharArray().map { it.getPriority() }.chunked(rucksack.length / 2)

    private fun Char.getPriority() = when (this) {
        in 'a'..'z' -> this - 'a' + 1
        in 'A'..'Z' -> this - 'A' + 27
        else -> throw IllegalArgumentException("Cannot get priority of '$this'")
    }

}
