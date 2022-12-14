package fr.deroffal.aoc_2022

import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    val input = getFileContentAsList("day09.txt")
    val day09 = Day09(input)

    println(
        """
            part 1 : ${day09.part1()}
    """.trimIndent()
    )
}

class Day09(val input: List<String>) {

    private var headPosition = Position(0, 0)
    private var tailPosition = Position(0, 0)

    private val tailPositions = mutableListOf(tailPosition)


    fun part1(): Int {
        input.forEach { line ->
            val steps = line.substringAfter(" ").toInt()
            val directions = line.substringBefore(" ").repeat(steps)

            directions.forEach {
                headPosition = headPosition.getNewHeadPosition(it)
                if (headPosition doNotTouch tailPosition) {
                    tailPosition = tailPosition.getNewTailPosition(headPosition)
                    tailPositions.add(tailPosition)
                }

            }
        }
        return tailPositions.distinct().size
    }

}

data class Position(val x: Int, val y: Int) {

    fun getNewHeadPosition(direction: Char): Position = when (direction) {
        'R' -> copy(x = x + 1)
        'U' -> copy(y = y + 1)
        'L' -> copy(x = x - 1)
        'D' -> copy(y = y - 1)
        else -> throw IllegalArgumentException("Unknown direction : $direction")
    }

    fun getNewTailPosition(headPosition: Position): Position =
        Position(this.x + (headPosition.x - this.x).sign, this.y + (headPosition.y - this.y).sign)

    infix fun doNotTouch(other: Position): Boolean = (other.x - this.x).absoluteValue > 1 || (other.y - this.y).absoluteValue > 1

}
