package fr.deroffal.aoc_2022

import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    val input = getFileContentAsList("day09.txt")
    val day09 = Day09(input)

    println(
        """
            part 1 : ${day09.part1()}
            part 2 : ${day09.part2()}
    """.trimIndent()
    )
}

class Day09(val input: List<String>) {

    fun part1(): Int = part(Position(0, 0), 1)
    fun part2(initialPosition: Position = Position(0, 0)): Int = part(initialPosition, 9)

    private fun part(initialPosition: Position, size: Int): Int {
        val firstKnot = createRope(initialPosition, size)

        val tailPositions = mutableListOf(initialPosition)

        input.forEach { line ->
            val steps = line.substringAfter(" ").toInt()
            val directions = line.substringBefore(" ").repeat(steps)

            directions.forEach {

                var current = firstKnot
                var next: Knot? = firstKnot.previous

                //move head ...
                current move it

                //then move all next ...
                while (next != null) {
                    if (next.position doNotTouch current.position) {
                        next moveTo next.position.getNewTailPosition(current.position)
                        if (next.previous == null) {
                            tailPositions.add(next.position)
                        }
                    } else {
                        //if the current do not move, any next won't
                        return@forEach
                    }
                    current = next
                    next = next.previous
                }

            }
        }
        return tailPositions.distinct().size
    }

    //Creation of the rope : from tail to head
    private fun createRope(initialPosition: Position, size: Int): Knot {
        var leadingKnot = Knot(null, initialPosition, size)
        (0 until size).forEach {
            leadingKnot = Knot(leadingKnot, initialPosition, size - it)
        }
        return leadingKnot
    }

}

data class Knot(val previous: Knot? = null, var position: Position = Position(0, 0), val rank: Int) {

    infix fun moveTo(position: Position) {
        this.position = position
    }

    infix fun move(direction: Char) {
        this moveTo position.getNewHeadPosition(direction)
    }

    override fun toString() = "$rank : $position - previous : ${previous?.position.toString()}"
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

    override fun toString() = "[$x,$y]"

}
