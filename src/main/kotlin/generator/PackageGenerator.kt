package generator

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import library.LibraryObject
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class PackageGenerator(val libObject: LibraryObject) {

    private val libName = "New${libObject.getLibName()}"
    private val libDir = "build/resources/test/"

    // For testing purposes
    // TODO: delete later
    // private val libDirForDebug = "src/main/java/"
    var root: Path? = Paths.get(".").normalize().toAbsolutePath()

    val resourceDirectory = Paths.get("src","main", "java")
    val absolutePath = resourceDirectory.toFile().absolutePath

    private val libDirForDebug = absolutePath /* System.getProperty("user.dir") */
    private val classList = libObject.getLibClassList()

    fun buildPackage() {

        val classes = getClasses()

        for(c in classes) {
            buildJavaFile(c)
        }
    }

    private fun buildJavaFile(c: TypeSpec) {
        val javaFile: JavaFile.Builder = JavaFile.builder(libName, c)
        javaFile.build().writeToFile(File(libDirForDebug))
        javaFile.build().writeToFile(File(libDir))
    }

    private fun getClasses(): List<TypeSpec> {
        val classGenerator = ClassGenerator(libObject, classList)
        return classGenerator.buildClasses()
    }
}