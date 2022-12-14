package fr.deroffal.aoc_2022

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Day09Test {
    private val input = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2"
    )
    private val day09 = Day09(input)

    @Test
    fun part1() = assertEquals(13, day09.part1())

    @Test
    fun getNewHeadPosition() {
        assertEquals(Position(4, 0), Position(3, 0).getNewHeadPosition('R'))
        assertEquals(Position(4, 4), Position(4, 3).getNewHeadPosition('U'))
        assertEquals(Position(1, 4), Position(2, 4).getNewHeadPosition('L'))
        assertEquals(Position(1, 2), Position(1, 3).getNewHeadPosition('D'))
    }

    @Test
    fun notTouches() = assertAll(
        { assertFalse(Position(2, 1) doNotTouch Position(1, 1)) },
        { assertFalse(Position(1, 3) doNotTouch Position(1, 4)) }
    )

    @Test
    @DisplayName("Touching, then head moves right")
    fun getNewTailPosition_touching_R() {
        val head = Position(3, 1)
        val tail = Position(1, 1)

        assertEquals(Position(2, 1), tail.getNewTailPosition(head))
    }

    @Test
    @DisplayName("Not touching, then head moves right")
    fun getNewTailPosition_notTouching_R() {
        val head = Position(2, 3)
        val tail = Position(1, 1)

        assertEquals(Position(2, 2), tail.getNewTailPosition(head))
    }

    @Test
    @DisplayName("Touching, then head moves left")
    fun getNewTailPosition_touching_L() {
        val head = Position(1, 1)
        val tail = Position(3, 1)

        assertEquals(Position(2, 1), tail.getNewTailPosition(head))
    }

    @Test
    @DisplayName("Touching, then head moves down")
    fun getNewTailPosition_touching_D() {
        val head = Position(1, 2)
        val tail = Position(1, 4)

        assertEquals(Position(1, 3), tail.getNewTailPosition(head))
    }

    @Test
    @DisplayName("Touching, then head moves up")
    fun getNewTailPosition_touching_U() {
        val head = Position(1, 4)
        val tail = Position(1, 2)

        assertEquals(Position(1, 3), tail.getNewTailPosition(head))
    }
}

