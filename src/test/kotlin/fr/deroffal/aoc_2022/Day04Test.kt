package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Day04Test {

    private val input = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",//
        "6-6,4-6",//
        "2-6,4-8"
    )

    private val day04 = Day04(input)

    @Test
    fun validateRegex() {
        val regex = day04.sectionRegex

        val simpleMatch = regex.matches("2-4,6-8")
        assertTrue(simpleMatch)
        val matchResult1 = regex.find("2-4,6-8")
        assertNotNull(matchResult1)
        assertEquals("2", matchResult1.groups[1]!!.value)
        assertEquals("4", matchResult1.groups[2]!!.value)
        assertEquals("6", matchResult1.groups[3]!!.value)
        assertEquals("8", matchResult1.groups[4]!!.value)

        assertTrue(regex.matches("22-65,22-66"))
        val matchResult2 = regex.find("22-65,22-66")
        assertNotNull(matchResult2)
        assertEquals("22", matchResult2.groups[1]!!.value)
        assertEquals("65", matchResult2.groups[2]!!.value)
        assertEquals("22", matchResult2.groups[3]!!.value)
        assertEquals("66", matchResult2.groups[4]!!.value)
    }

    @Test
    fun part1() = assertEquals(2, day04.part1())

    @Test
    fun part2() = assertEquals(4, day04.part2())
}
