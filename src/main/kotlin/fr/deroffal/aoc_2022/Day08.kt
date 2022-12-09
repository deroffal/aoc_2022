package fr.deroffal.aoc_2022

fun main() {
    val input = getFileContentAsList("day08.txt").takeWhile { it.isNotEmpty() }
    val day08 = Day08(input)

    println(
        """
            part 1 : ${day08.part1()}
            part 2 : ${day08.part2()}
    """.trimIndent()
    )
}

class Day08(input: List<String>) {

    private val grid: List<List<Int>> = input.map { line -> line.toList().map { it.digitToInt() } }

    private val length = input.size
    private val width = input.first().length

    val forest = Forest(grid)

    fun part1(): Int = (1 until length - 1)
        .flatMap { y -> (1 until width - 1).map { x -> x to y } }
        .count { forest.isTreeVisible(it.first, it.second) } + forest.countOuterTrees()

    fun part2(): Int = (1 until length - 1)
        .flatMap { y -> (1 until width - 1).map { x -> x to y } }
        .maxOf { forest.getScenicScore(it) }
}


class Forest(private val grid: List<List<Int>>) {

    private val length = grid.size
    private val width = grid.first().size

    fun getTree(x: Int, y: Int): Int = grid[y][x]
    fun getTreesForY(y: Int): List<Int> = grid[y]
    fun getTreesForX(x: Int): List<Int> = grid.map { it[x] }
    fun isTreeVisible(x: Int, y: Int): Boolean {
        val tree = getTree(x, y)

        val highestNorth = getTreesNorthFrom(x, y).max()
        val highestSouth = getTreesSouthFrom(x, y).max()
        val highestWest = getTreesWestFrom(x, y).max()
        val highestEast = getTreesEastFrom(x, y).max()

        return listOf(highestNorth, highestSouth, highestEast, highestWest).any { tree > it }
    }

    fun getTreesNorthFrom(x: Int, y: Int) = getTreesForX(x).subList(0, y).reversed()
    fun getTreesSouthFrom(x: Int, y: Int) = getTreesForX(x).subList(y + 1, length)
    fun getTreesEastFrom(x: Int, y: Int) = getTreesForY(y).subList(x + 1, width)
    fun getTreesWestFrom(x: Int, y: Int) = getTreesForY(y).subList(0, x).reversed()

    fun countOuterTrees() = 2 * width + 2 * length - 4

    fun getScenicScore(pair: Pair<Int, Int>): Int = getScenicScore(pair.first, pair.second)
    fun getScenicScore(x: Int, y: Int): Int {
        val tree = getTree(x, y)
        val northScore = getTreesNorthFrom(x, y).takeUntil { it >= tree }.count()
        val southScore = getTreesSouthFrom(x, y).takeUntil { it >= tree }.count()
        val westScore = getTreesWestFrom(x, y).takeUntil { it >= tree }.count()
        val eastScore = getTreesEastFrom(x, y).takeUntil { it >= tree }.count()
        return northScore * southScore * westScore * eastScore
    }

    private inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
        val list = mutableListOf<T>()
        for (item in this) {
            list.add(item)
            if (predicate(item)) {
                break
            }
        }
        return list
    }
}
