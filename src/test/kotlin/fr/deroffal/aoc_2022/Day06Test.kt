package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun part1() {
        assertEquals(5, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").part1())
        assertEquals(6, Day06("nppdvjthqldpwncqszvftbrmjlhg").part1())
        assertEquals(10, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").part1())
        assertEquals(11, Day06("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").part1())
    }

    @Test
    fun part2() {
        assertEquals(19, Day06("mjqjpqmgbljsphdztnvjfqwrcgsmlb").part2())
        assertEquals(23, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").part2())
        assertEquals(23, Day06("nppdvjthqldpwncqszvftbrmjlhg").part2())
        assertEquals(29, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").part2())
        assertEquals(26, Day06("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").part2())
    }
}
