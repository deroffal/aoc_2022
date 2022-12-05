package fr.deroffal.aoc_2022

import java.io.File

fun getFileContent(filename: String) = file(filename).readText().trim()
fun getFileContentAsList(filename: String) = file(filename).readLines()

private fun file(filename: String) = File(ClassLoader.getSystemResource(filename).toURI())
