package fr.deroffal.aoc_2022

import java.io.File

fun getFileContent(filename: String) = File(ClassLoader.getSystemResource(filename).toURI()).readText().trim()
