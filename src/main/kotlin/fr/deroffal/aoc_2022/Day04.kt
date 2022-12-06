package fr.deroffal.aoc_2022

fun main() {
    val input = getFileContentAsList("day04.txt")
    val day04 = Day04(input)

    println(
        """
        part 1 : ${day04.part1()}
        part 2 : ${day04.part2()}
    """.trimIndent()
    )
}

class Day04(private val sectionAssignments: List<String>) {

    val sectionRegex = Regex("(\\d*)-(\\d*),(\\d*)-(\\d*)")

    fun part1() = getSectionRanges().count { it.first fullyOverlaps it.second || it.second fullyOverlaps it.first }

    fun part2() = getSectionRanges().count { it.first overlaps it.second || it.second overlaps it.first }

    private fun getSectionRanges() = sectionAssignments.map {
        val matchResult = sectionRegex.find(it)!!
        IntRange(
            matchResult.groups[1]!!.value.toInt(),
            matchResult.groups[2]!!.value.toInt()
        ) to IntRange(
            matchResult.groups[3]!!.value.toInt(),
            matchResult.groups[4]!!.value.toInt()
        )
    }

    private infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = other.first in this && other.last in this
    private infix fun IntRange.overlaps(other: IntRange): Boolean = other.first in this || other.last in this

}
