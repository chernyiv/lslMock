package generator

import library.LibraryObject
import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.asg.Automaton
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.Library
import java.io.File
import kotlin.text.StringBuilder

interface Generator {

    // Will use Java Poet library for code generation, instead of StringBuilder
    fun generate(libraryObject: LibraryObject)
}

/* fun main(args: Array<String>) {

        val lslFile = File("src/test/resources/SimpleRandomization.lsl")
        val library = analyzeSpecification(lslFile)

        generateLibrary(library, lslFile)
}

fun analyzeSpecification(lslFile: File): Library {
        val librarySpecification = LibSL(lslFile.path)
        return librarySpecification.loadFromFile(lslFile)
}

fun generateLibrary(library: Library, lslFile: File) {
        val libName = "new${lslFile.nameWithoutExtension}"
        val libDir = "src/test/resources/$libName"
        File(libDir).mkdir();
        generateClasses(libName, libDir, library, library.automata)
        print(library);
}

fun generateClasses(libName: String, libDir: String, library: Library, automatonList: List<Automaton>) {
        val sb = StringBuilder()
        for (automaton in automatonList) {
                val contents = sb.run {
                        append("package $libName;\n"+
                                "import org.jeasy.random.EasyRandom;" +
                                "\nimport org.jeasy.random.EasyRandomParameters;\n\n"
                        )

                        for (inc in library.includes) {
                                append("import $inc;\n\n")
                        }

                        append("public class ${automaton.type.name} { \n\n" +
                                "EasyRandom g = new EasyRandom();\n\n" +
                                "\tstatic ${library.metadata.name}.${automaton.type.name.replace("$", ".")} internal;\n\n"
                        )
                        append("\t${automaton.type.name}(${library.metadata.name}.${automaton.type.name.replace("$", ".")} internal) " +
                                "{ this.internal = internal; }\n\n")

                        generateFunctions(library, automaton.functions, sb)

                        append("\n}")

                        toString()
                }

                File("$libDir/${automaton.name}.java").writeText(contents)
                sb.delete(0, sb.length)
        }

}

fun generateFunctions(library: Library, functionList: List<Function>, sb: StringBuilder) {
        for (function in functionList) {

                //FIX_THIS!!!!

                if (function.returnType == null) {
                        sb.append("\t\tpublic void ")
                } else {
                        if (function.returnType!!.name != "String") {
                                sb.append("\t\tpublic ${getFunctionName(function)} ")
                        } else {
                                sb.append("\t\tpublic ${getFunctionName(function)} ")
                        }
                }
                sb.append(function.name)

                generateArgs(function, sb);
                generateBody(library, function, sb);
        }
}

fun generateArgs(function:Function, sb:StringBuilder ) {

        sb.append("(")
        for (arg in function.args) {
                sb.append(arg.type.name + " ")
                if (arg.annotation != null) {
                        sb.append("/*${arg.annotation!!.name}*/" + " ")
                }
                sb.append(arg.name)

                if (function.args.size > 1 && arg.index + 1 != function.args.size) {
                        sb.append(", ")
                }
        }

        sb.append(")")

}

fun generateBody(library: Library, function: Function, sb: StringBuilder) {
        val simpleTypes = arrayListOf("String", "Integer", "char", "byte[]", "byte", "int", "short",
                "long", "float", "double", "boolean")
        sb.append(" { \n")

        if(function.returnType != null){

                val internalTypes = arrayListOf<String>()

                for(automaton in library.automata){
                        if(function.returnType!!.name == automaton.name){
                                internalTypes.add(function.returnType!!.name)
                        }
                }

                sb.append("\t\t\t")
                var isOriginalType = false
                for(t in simpleTypes) {
                        if(function.returnType!!.name != t){
                                sb.append("${library.metadata.name}.")
                                isOriginalType = true
                                break
                        } else {
                                break
                        }
                }

                sb.append("${function.returnType!!.name.replace("$", ".")} ${function.returnType!!.name[0].lowercase()} = internal.${function.name}(")
                for(arg in function.args) {
                        sb.append(arg.name)
                        for(inType in internalTypes){
                                if(function.returnType!!.name == inType) {
                                        sb.append(".internal")
                                }
                        }
                        if (function.args.size > 1 && arg.index + 1 != function.args.size) {
                                sb.append(", ")
                        }
                }
                sb.append(");\n")
                sb.append("\t\t\t")

                if(function.typeAnnotation?.name == "Fuzz"){
                        sb.append("${function.returnType!!.name[0].lowercase()} = g.nextObject(${library.metadata.name}.${function.returnType!!.name}.class);\n")
                        sb.append("\t\t\treturn new ${function.returnType!!.name}(${function.returnType!!.name[0].lowercase()});")
                } else {
                        for(a in library.automata) {
                                if(function.returnType!!.name == a.name) {
                                        sb.append("return this;")
                                }
                        }

                        if(isOriginalType) {
                                sb.append("return new ${function.returnType!!.name}(${function.returnType!!.name[0].lowercase()});")
                        } else {
                                sb.append("return ${function.returnType!!.name[0].lowercase()};")
                        }
                }

        } else {
                sb.append("\t\tinternal.${function.name}();\n")
        }

        sb.append("\n\t\t}\n\n")
}

fun getFunctionName(function: Function): String? {
        return function.returnType?.name
} */

