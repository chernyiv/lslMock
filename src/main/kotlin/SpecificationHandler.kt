import java.io.File

interface SpecificationHandler {

    // Tool receives specification and uses parser to get tree from it
    fun start(lslFile: File)

    /*fun start(lslFile: File) {

        // Checking if the tool received correct file
        if(lslFile.extension != "lsl") {
            throw Exception("You must provide LibSL Specification")
        }

        // Abstract semantic graph from LibSL parser
        val libName = "new${lslFile.nameWithoutExtension}"
        File("src/test/resources/$libName").mkdir();
        val tree = Tree()
        val libTree = LibSL(lslFile.path).loadFromFile(lslFile)
        tree.setLibrary(libName, libTree)
    } */
}