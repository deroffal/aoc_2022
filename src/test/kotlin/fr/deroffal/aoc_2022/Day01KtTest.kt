package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day01KtTest {

    private val input = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""

    private val day01 = Day01(input, "\n")

    @Test
    fun part1() {
        val part1 = day01.part1()
        Assertions.assertEquals(24_000, part1)
    }

    @Test
    fun part2() {
        val part2 = day01.part2()
        Assertions.assertEquals(45_000, part2)
    }
}
