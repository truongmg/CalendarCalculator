fun readResourceFileAsLines(fileName: String): String?
    = object {}.javaClass.getResource(fileName)?.readText()

fun String.splitToList(delimiters: String): List<String>
    = this.split(delimiters).toList()
