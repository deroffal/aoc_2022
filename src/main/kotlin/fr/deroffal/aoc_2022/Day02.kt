package fr.deroffal.aoc_2022

import fr.deroffal.aoc_2022.RoundResult.*
import fr.deroffal.aoc_2022.Shape.*

fun main() {
    val input = getFileContentAsList("day02.txt")
    val day02 = Day02(input)

    println(
        """
        part 1 : ${day02.part1()}
        part 2 : ${day02.part2()}
    """.trimIndent()
    )
}

class Day02(private val input: List<String>) {
    fun part1() = this.input
        .map { Shape.of(it[2]) to Shape.of(it[0]) }
        .sumOf { it.first.against(it.second) }

    fun part2() = this.input
        .map { Shape.of(it[0]) to RoundResult.from(it[2]) }
        .sumOf { it.second.findShapeToPlay(it.first).against(it.first) }
}

enum class Shape(private val points: Int, val inputs: List<Char>) {
    ROCK(1, listOf('A', 'X')),
    PAPER(2, listOf('B', 'Y')),
    SCISSORS(3, listOf('C', 'Z'));

    companion object {
        fun of(input: Char): Shape = Shape.values().find { input in it.inputs } ?: throw RuntimeException("Can't parse input !")
    }

    fun against(other: Shape): Int {
        return this.points + getScoreAgainst(other)
    }

    private fun getScoreAgainst(other: Shape): Int {
        return if (this == other) {
            DRAW.score
        } else when (this) {
            ROCK -> return if (other == PAPER) {
                LOSE.score
            } else {
                WIN.score
            }

            PAPER -> return if (other == SCISSORS) {
                LOSE.score
            } else {
                WIN.score
            }

            SCISSORS -> return if (other == ROCK) {
                LOSE.score
            } else {
                WIN.score
            }
        }
    }
}

enum class RoundResult(private val input: Char, val score: Int) {
    LOSE('X', 0),
    DRAW('Y', 3),
    WIN('Z', 6);

    companion object {
        fun from(input: Char) = RoundResult.values().find { input == it.input } ?: throw RuntimeException("Can't parse input !")
    }

    fun findShapeToPlay(opponentShape: Shape): Shape {

        return when (this) {
            DRAW -> opponentShape

            LOSE -> when (opponentShape) {
                ROCK -> SCISSORS
                SCISSORS -> PAPER
                PAPER -> ROCK
            }

            WIN -> when (opponentShape) {
                ROCK -> PAPER
                SCISSORS -> ROCK
                PAPER -> SCISSORS
            }
        }
    }
}


