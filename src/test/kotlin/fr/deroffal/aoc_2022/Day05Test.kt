package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day05Test {

    private val input = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
    """.trimIndent()
        .split("\n")

    private val day05 = Day05(input)

    @Test
    fun parseInputForCrateMover9000() {
        val instructions = day05.parseInstructionsForCrateMover9000()

        assertEquals(4, instructions.size)

        val instruction0 = instructions[0]
        assertEquals(1, instruction0.quantity)
        assertEquals(2, instruction0.source)
        assertEquals(1, instruction0.target)
    }

    @Test
    fun parseInputForCrateMover9001() {
        val instructions = day05.parseInstructionsForCrateMover9001()

        assertEquals(4, instructions.size)

        val instruction0 = instructions[0]
        assertEquals(1, instruction0.quantity)
        assertEquals(2, instruction0.source)
        assertEquals(1, instruction0.target)
    }

    @Test
    fun createStacks() {
        val stacks = day05.createStacks()

        assertEquals(ArrayDeque(mutableListOf('Z', 'N')), stacks[0])
        assertEquals(ArrayDeque(mutableListOf('M', 'C', 'D')), stacks[1])
        assertEquals(ArrayDeque(mutableListOf('P')), stacks[2])
    }

    @Test
    fun part1() = assertEquals("CMZ", day05.part1())

    @Test
    fun part2() = assertEquals("MCD", day05.part2())

    private val realDay05 = Day05(getFileContentAsList("day05.txt"))

    @Test
    fun day05() {
       assertEquals("RTGWZTHLD", realDay05.part1())
       assertEquals("STHGRZZFR", realDay05.part2())
    }
}
