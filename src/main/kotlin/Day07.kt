private const val MAX_FILE_SIZE = 100_000

data class DirectoryOrFile(val name: String, val size: Long?, val parentDirectory: String?, val subdirectories: List<DirectoryOrFile>?, val files: List<Long>?) {
    fun getSumOfFileSizes(): Long = subdirectories?.let { subdirectories ->
        subdirectories.sumOf { it.getSumOfFileSizes() }
            .plus(files?.sum() ?: 0)
    } ?: 0

}

fun main() {

    val input = readInput("Day07Input").split("\n")
    val fileTree = HashMap<String, MutableMap<Long, String>>()
    var currentDirectory = "/"

    fileTree[currentDirectory] = mutableMapOf()

    for (line in input.subList(2, input.lastIndex)) {
        when (line.split(" ").first()) {
            "$" -> {
                val command = line.removePrefix("$ ").split(" ")
                when (command.first()) {
                    "cd" ->
                        currentDirectory =
                            (if (command.last() == "..") getParentDirectoryFor(currentDirectory) else getNewDirectoryName(
                                currentDirectory,
                                command
                            ))

                    "ls" -> continue
                    else -> throw error("command unknown: <${command.first()}>")
                }
            }

            "dir" -> {
                val name = line.removePrefix("dir ")
                val subDirectory = if (currentDirectory == "/") "$currentDirectory$name" else "$currentDirectory/$name"
                fileTree[subDirectory] = mutableMapOf()
            }

            else -> {
                val fileInfo = line.split(" ")
                fileTree[currentDirectory]!![fileInfo.first().toLong()] = fileInfo.last()
            }
        }
    }

    findDirectoriesWithLessThanMaxFileSize(fileTree).sum().also { println(it) }
}

fun findDirectoriesWithLessThanMaxFileSize(fileTree: java.util.HashMap<String, MutableMap<Long, String>>): MutableList<Long> {
    val directories = mutableListOf<Long>()
    fileTree.keys.forEach { key ->
        val sum = fileTree[key]!!.keys.sum()
        if (sum <= MAX_FILE_SIZE) {
            directories.add(sum)
        }
    }
    return directories
}

private fun getNewDirectoryName(currentDirectory: String, command: List<String>) =
    if (currentDirectory == "/") "/${command.last()}" else "$currentDirectory/${command.last()}"

fun getParentDirectoryFor(currentDirectory: String): String =
    currentDirectory
        .split("/")
        .drop(1)
        .run {
            return@run if (this.size == 1) "/" else "/${this.dropLast(1).joinToString("/")}"
        }