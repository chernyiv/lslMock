package generator

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import org.jetbrains.research.libsl.asg.Automaton
import org.jetbrains.research.libsl.asg.Library
import java.io.File

class PackageGenerator(val libTree: Library) {

    private val libName = getLibName(libTree)
    private val libDir = "build/resources/test/$libName"

    fun buildPackage() {

        val classList = getClasses(libTree.automata)
        makeLibDir(libName)

        for(c in classList) {
            buildJavaFile(c)
        }
    }

    private fun buildJavaFile(c: TypeSpec) {
        val javaFile: JavaFile.Builder = JavaFile.builder(libName, c)
        javaFile.build().writeToFile(File(libDir))
    }

    private fun getClasses(c: List<Automaton>): List<TypeSpec> {
        val classGenerator: ClassGenerator = ClassGenerator(libTree.automata)
        return classGenerator.buildClasses()
    }

    private fun getLibName(libTree: Library): String {
        return "New${libTree.metadata.name}"
    }

    private fun makeLibDir(name: String) {
        File("build/resources/test/$libName").mkdir();
    }

    /*private fun getFile(name: String): File {
        return File("$libDir/$name")
    } */

}