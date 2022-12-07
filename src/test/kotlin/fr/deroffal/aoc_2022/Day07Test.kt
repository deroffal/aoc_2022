package fr.deroffal.aoc_2022

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {
    private val input = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k"
    )

    private val day07 = Day07(input)

    @Test
    fun part1() = assertEquals(95437, day07.part1())
    @Test
    fun part2() = assertEquals(24933642, day07.part2())

}

class DirectoryTest {

    @Test
    fun addDirectory() {
        val root = Directory(null, "/")
        val actualDir = root.addDirectory("dir")

        assertEquals("dir", actualDir.name)
        assertEquals(root, actualDir.parent)
    }

    @Test
    fun addFile() {
        val root = Directory(null, "/")
        root.addFile("1", 1)
        root.addFile("2", 2)

        assertEquals(3, root.getSize())
    }

    @Test
    fun getSize_recursive() {
        val root = Directory(null, "/")
        root.addFile("1", 1)
        root.addFile("2", 2)

        val dir1 = root.addDirectory("dir1")
        dir1.addFile("3", 3)

        val dir2 = root.addDirectory("dir2")
        dir2.addFile("4", 4)

        assertEquals(10, root.getSize())
    }

}
