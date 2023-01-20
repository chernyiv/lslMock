import generator.PackageGenerator
import library.LibraryObject
import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.asg.Library
import java.io.File

class SpecificationHandler(val lslFile: File) {

    fun startHandler() {

        if(lslFile.extension != "lsl") {
            throw Exception("You must provide LibSL Specification")
        }
        val libTree = getLibTree(lslFile)
        val libraryObject = LibraryObject(libTree)
        println(libraryObject.getLibName())
        println(libraryObject.getLibClassList())
        println(libraryObject.getTypeMap())
        println(libraryObject.getImports())
        println(libraryObject.getIncludes())
        val packageGenerator = PackageGenerator(libraryObject)
        packageGenerator.buildPackage()
    }

    private fun getLibTree(lslFile: File): Library {
        return LibSL(lslFile.path).loadFromFile(lslFile)
    }
}
