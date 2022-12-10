import org.junit.jupiter.api.Test

class Day07Test {

    @Test
    fun getParentDirectory__success__root() {
        val currentDirectory = "/dir01"

        val result = getParentDirectoryFor(currentDirectory)

        assert(result == "/")
    }

    @Test
    fun getParentDirectory__success__twoDirs() {
        val currentDirectory = "/dir01/dir02"

        val result = getParentDirectoryFor(currentDirectory)

        assert(result == "/dir01")
    }

    @Test
    fun getParentDirectory__success__manyDirs() {
        val currentDirectory = "/dir01/dir02/dir03/dir04"

        val result = getParentDirectoryFor(currentDirectory)

        assert(result == "/dir01/dir02/dir03")
    }

}
