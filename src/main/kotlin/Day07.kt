private const val MAX_FILE_SIZE = 100_000
private const val TOTAL_DISK_SIZE = 70_000_000
private const val MIN_FREE_SPACE_FOR_UPDATE = 30_000_000

data class Directory(
    val name: String,
    val parentDirectory: String,
    val subdirectories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<Long> = mutableListOf(),
) {
    fun getSumOfFileSizes(): Long = subdirectories.sumOf { directory ->
        directory.getSumOfFileSizes()
    }.plus(files.sum())
}

fun main() {

    val input = readInput("Day07Input").split("\n")
    val fileTree = HashMap<String, Directory>()
    var currentDirectory = Directory("/", "/")

    fileTree[currentDirectory.name] = currentDirectory

    for (line in input.subList(2, input.lastIndex)) {
        when (line.split(" ").first()) {
            "$" -> {
                val command = line.removePrefix("$ ").split(" ")
                when (command.first()) {
                    "cd" ->
                        currentDirectory = if (command.last() == "..") {
                            fileTree[currentDirectory.parentDirectory] ?: error("no parent directory")
                        } else {
                            fileTree[determineDirectoryName(currentDirectory, command.last())]
                                ?: error("directory ${command.last()} not found")
                        }

                    "ls" -> continue
                }
            }

            "dir" -> {
                val subDirectory = Directory(
                    name = determineDirectoryName(currentDirectory, line.removePrefix("dir ")),
                    parentDirectory = currentDirectory.name,
                )
                currentDirectory.subdirectories.add(subDirectory)
                fileTree[subDirectory.name] = subDirectory
            }

            else -> {
                val fileInfo = line.split(" ")
                fileTree[currentDirectory.name]?.files?.add(fileInfo.first().toLong())
            }
        }
    }

    findDirectoriesWithLessThanMaxFileSize(fileTree).sum().also { println(it) }
    val usedDiskSpace = fileTree["/"]!!.getSumOfFileSizes()
    val freeDiskSpace = TOTAL_DISK_SIZE - usedDiskSpace // 26_496_260
    val directorySizeWanted = MIN_FREE_SPACE_FOR_UPDATE - freeDiskSpace // 3_503_740
    println(findDirectoryWithFileSize(directorySizeWanted, fileTree).getSumOfFileSizes())
}

fun findDirectoryWithFileSize(fileSize: Long, fileTree: HashMap<String, Directory>) =
    fileTree.values.filter {
        it.getSumOfFileSizes() >= fileSize
    }.minBy {
        it.getSumOfFileSizes()
    }

fun findDirectoriesWithLessThanMaxFileSize(fileTree: HashMap<String, Directory>): MutableList<Long> {
    val directories = mutableListOf<Long>()
    fileTree.keys.forEach { key ->
        val sum = fileTree[key]!!.getSumOfFileSizes()
        if (sum <= MAX_FILE_SIZE) {
            directories.add(sum)
        }
    }
    return directories
}

private fun determineDirectoryName(currentDirectory: Directory, name: String) =
    if (currentDirectory.name == "/") "/$name" else "${currentDirectory.name}/$name"
