package fr.deroffal.aoc_2022


fun main() {
    val input = getFileContent("day06.txt")
    val day06 = Day06(input)

    println(
        """
            part 1 : ${day06.part1()}
            part 2 : ${day06.part2()}

    """.trimIndent()
    )
}

class Day06(private val input: String) {
    fun part1() = findFirstStartOfMessage(4)
    fun part2() = findFirstStartOfMessage(14)
    private fun findFirstStartOfMessage(signalLength: Int) =
        input.indices.first { input.substring(it, it + signalLength).toSet().size == signalLength } + signalLength
}
