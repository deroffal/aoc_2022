package fr.deroffal.aoc_2022


fun main() {
    val input = getFileContentAsList("day05.txt")
    val day05 = Day05(input)

    println(
        """
            part 1 : ${day05.part1()}
            part 2 : ${day05.part2()}
    """.trimIndent()
    )
}

private typealias Stacks = List<ArrayDeque<Char>>

class Day05(private val input: List<String>) {

    //    move 1 from 2 to 1
    private val instructionRegex = Regex("move (\\d*) from (\\d*) to (\\d*)")

    fun part1(): String {
        val instructions = parseInstructionsForCrateMover9000()
        val stacks = createStacks()
        instructions.forEach { it.execute(stacks) }
        return stacks.map { it.last() }.joinToString(separator = "")
    }

    fun part2(): String {
        val instructions = parseInstructionsForCrateMover9001()
        val stacks = createStacks()
        instructions.forEach { it.execute(stacks) }
        return stacks.map { it.last() }.joinToString(separator = "")
    }

    fun parseInstructionsForCrateMover9000(): List<InstructionCrateMover9000> =
        parseInstructions { quantity, source, target -> InstructionCrateMover9000(quantity, source, target) }

    fun parseInstructionsForCrateMover9001(): List<InstructionCrateMover9001> =
        parseInstructions { quantity, source, target -> InstructionCrateMover9001(quantity, source, target) }


    private fun <T> parseInstructions(instructionCreator: (a: Int, b: Int, c: Int) -> T) = input.filter { instructionRegex.matches(it) }
        .map { instructionRegex.find(it)!!.groups }
        .map {
            val quantity = it[1]!!.value.toInt()
            val source = it[2]!!.value.toInt()
            val target = it[3]!!.value.toInt()
            instructionCreator.invoke(quantity, source, target)
        }


    fun createStacks(): Stacks {
        val chunked: List<List<Char>> = input.filter { it.contains("[") }
            .map { it.chunked(4).map { chunk -> chunk[1] } }

        val stacks: Stacks = (0 until chunked.maxOf { it.size }).map { ArrayDeque() }

        chunked.indices.reversed().forEach { stackNumber ->
            chunked[stackNumber].forEachIndexed { index, c ->
                if (c != ' ') {
                    stacks[index].add(c)
                }
            }
        }
        return stacks
    }
}

class InstructionCrateMover9000(val quantity: Int, source: Int, target: Int) : Instruction(source, target) {
    override fun execute(stacks: Stacks) {
        val sourceStack = getSourceStack(stacks)
        val targetStack = getTargetStack(stacks)

        (1..quantity).forEach { _ ->
            targetStack.addLast(sourceStack.removeLast())
        }
    }
}

class InstructionCrateMover9001(val quantity: Int, source: Int, target: Int) : Instruction(source, target) {
    override fun execute(stacks: Stacks) {
        val sourceStack = getSourceStack(stacks)
        val targetStack = getTargetStack(stacks)

        (1..quantity).map { _ -> sourceStack.removeLast() }.reversed().forEach {
            targetStack.addLast(it)
        }
    }

}

abstract class Instruction(val source: Int, val target: Int) {
    abstract fun execute(stacks: Stacks)

    protected fun getTargetStack(stacks: Stacks) = stacks[target - 1]

    protected fun getSourceStack(stacks: Stacks) = stacks[source - 1]
}
