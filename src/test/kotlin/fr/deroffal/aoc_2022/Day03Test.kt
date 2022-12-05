package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    private val input = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    private val day03 = Day03(input)

    @Test
    fun part1() {
        val part1 = day03.part1()
        assertEquals(157, part1)
    }

    @Test
    fun part2() {
        val part2 = day03.part2()
        assertEquals(70, part2)
    }
}
