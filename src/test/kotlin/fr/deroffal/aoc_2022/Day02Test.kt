package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day02Test {

    private val input = listOf(
        "A Y",
        "B X",
        "C Z"
    )
    private val day02 = Day02(input)

    @Test
    fun part1() {
        val part1 = day02.part1()
        Assertions.assertEquals(15, part1)
    }

    @Test
    fun part2() {
        val part1 = day02.part2()
        Assertions.assertEquals(12, part1)
    }

    private val realDay02 = Day02(getFileContentAsList("day02.txt"))

    @Test
    fun day02() {
        Assertions.assertEquals(12794, realDay02.part1())
        Assertions.assertEquals(14979, realDay02.part2())
    }
}
