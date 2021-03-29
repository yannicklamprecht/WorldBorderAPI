/*
 * https://github.com/MiniDigger/Toothpick/blob/master/buildSrc/src/main/kotlin/Util.kt
 */


import java.io.File
import java.util.*

fun cmd(vararg args: String, directory: File, printToStdout: Boolean = false): Pair<Int, String?> {
    val p = ProcessBuilder()
        .command(*args)
        .redirectErrorStream(true)
        .directory(directory)
        .start()
    val output = p.inputStream.bufferedReader().use {
        val lines = LinkedList<String>()
        it.lines().peek { line: String -> lines.add(line) }.forEach { line ->
            if (printToStdout) {
                println(line)
            }
        }
        lines.joinToString(separator = "\n")
    }
    val exit = p.waitFor()
    return exit to output
}
