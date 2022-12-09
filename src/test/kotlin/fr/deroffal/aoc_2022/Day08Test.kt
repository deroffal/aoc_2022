package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day08Test {

    private val input = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )

    private val day08 = Day08(input)
    private val forest = day08.forest


    @Test
    fun part1() = assertEquals(21, day08.part1())
    @Test
    fun part2() = assertEquals(8, day08.part2())

    @Test
    fun getTree() = assertAll(
        { assertEquals(3, forest.getTree(0, 0)) },
        { assertEquals(5, forest.getTree(1, 1)) },
        { assertEquals(1, forest.getTree(3, 1)) },
        { assertEquals(3, forest.getTree(1, 3)) },
    )

    @Test
    fun getTreesInLine() = assertEquals(listOf(3, 5, 3, 5, 3), forest.getTreesForX(2))

    @Test
    fun getTreesInColumn() = assertEquals(listOf(6, 5, 3, 3, 2), forest.getTreesForY(2))

    @Test
    fun getTreesNorthFrom() = assertEquals(listOf(5, 5, 0), forest.getTreesNorthFrom(1, 3))

    @Test
    fun getTreesSouthFrom() = assertEquals(listOf(5, 3, 5), forest.getTreesSouthFrom(1, 1))

    @Test
    fun getTreesEastFrom() = assertEquals(listOf(5, 4, 9), forest.getTreesEastFrom(1, 3))

    @Test
    fun getTreesWestFrom() = assertEquals(listOf(5, 5, 2), forest.getTreesWestFrom(3, 1))

    @Test
    fun isTreeVisible() =
        assertAll(
            { assertTrue(forest.isTreeVisible(1, 1)) },
            { assertTrue(forest.isTreeVisible(2, 1)) },
            { assertFalse(forest.isTreeVisible(3, 1)) },

            { assertTrue(forest.isTreeVisible(1, 2)) },
            { assertFalse(forest.isTreeVisible(2, 2)) },
            { assertTrue(forest.isTreeVisible(3, 2)) },

            { assertFalse(forest.isTreeVisible(1, 3)) },
            { assertTrue(forest.isTreeVisible(2, 3)) },
            { assertFalse(forest.isTreeVisible(3, 3)) },
        )

    @Test
    fun countOuterTrees() = assertEquals(16, forest.countOuterTrees())

    @Test
    fun getScenicScore() = assertAll(
        { assertEquals(4, forest.getScenicScore(2, 1)) },
        { assertEquals(8, forest.getScenicScore(2, 3)) }
    )

    private val realDay08 = Day08(getFileContentAsList("day08.txt"))

    @Test
    fun day08() {
        assertEquals(1820, realDay08.part1())
    }
}

