import generator.PackageGenerator
import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.asg.Library
import java.io.File

class SpecificationHandler(val lslFile: File) {

    fun startHandler() {

        if(lslFile.extension != "lsl") {
            throw Exception("You must provide LibSL Specification")
        }

        val packageGenerator = PackageGenerator(getLibTree(lslFile))
        packageGenerator.buildPackage()
    }

    private fun getLibTree(lslFile: File): Library {
        return LibSL(lslFile.path).loadFromFile(lslFile)
    }
}
