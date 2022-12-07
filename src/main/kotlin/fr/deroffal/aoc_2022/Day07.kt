package fr.deroffal.aoc_2022


fun main() {
    val input = getFileContentAsList("day07.txt")
    val day07 = Day07(input)

    println(
        """
            part 1 : ${day07.part1()}
            part 2 : ${day07.part2()}
    """.trimIndent()
    )
}


class Day07(val input: List<String>) {

    private val filesystem = parseFilesystem()

    private fun parseFilesystem(): Directory {

        val fileRegex = Regex("(\\d*)\\s(.*)")

        val root = Directory(null, "/")

        var currentDirectory: Directory = root

        input
            .filter { it != "$ ls" }
            .forEach { output ->
                when {
                    output.startsWith("$ cd ") -> {
                        currentDirectory = when (val target = output.substringAfter("$ cd ")) {
                            "/" -> root
                            ".." -> currentDirectory.parent!!
                            else -> currentDirectory.getDirectory(target)
                        }
                    }

                    output.startsWith("dir ") -> currentDirectory.addDirectory(output.substringAfter("dir "))

                    output.matches(fileRegex) -> fileRegex.find(output)?.let {
                        val groups = it.groups
                        currentDirectory.addFile(groups[2]!!.value, groups[1]!!.value.toInt())
                    }

                    else -> throw IllegalArgumentException("Unknown instruction : $output")
                }
            }
        return root
    }

    fun part1(): Int = filesystem.findDirectoriesBySizeLessThan(100_000)
        .sumOf { it.getSize() }

    fun part2(): Int = filesystem.getAllDirectories()
        .filter { it.getSize() > filesystem.getSize() - (70_000_000 - 30_000_000) }
        .minBy { it.getSize() }.getSize()
}

class Directory(val parent: Directory?, val name: String) {

    private val files: MutableList<File> = mutableListOf()
    private val directories: MutableList<Directory> = mutableListOf()

    fun addDirectory(name: String): Directory = Directory(this, name).apply { this@Directory.directories.add(this) }
    fun addFile(name: String, size: Int): File = File(name, size).apply { files.add(this) }

    fun getDirectory(name: String): Directory = directories.find { it.name == name } ?: throw IllegalArgumentException("Unknown directory : $name")

    fun getSize(): Int = files.sumOf { it.size } + directories.sumOf { it.getSize() }

    fun findDirectoriesBySizeLessThan(size: Int): List<Directory> =
        directories.filter { it.getSize() < size } + directories.flatMap { it.findDirectoriesBySizeLessThan(size) }

    fun getAllDirectories(): List<Directory> = directories.flatMap { it.getAllDirectories() } + this

    override fun toString() = "${this.name} - ${this.getSize()}"
}

class File(private val name: String, val size: Int) {
    override fun toString() = "$name - $size"
}
