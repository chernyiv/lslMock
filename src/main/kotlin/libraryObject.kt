import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.asg.Automaton
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.Library
import java.io.File
import kotlin.text.StringBuilder

fun main(args: Array<String>) {

        val lslFile = File("src/test/resources/okhttp3.lsl")
        //val librarySpecification = LibSL("src/test/resources/okhttp3.lsl")
        //val library = librarySpecification.loadFromFile(lslFile)
        val library = analyzeSpecification(lslFile)

        generateLibrary(library)
}

fun startGeneration(lslFile: File, projectPath: String) {
        projectPath
}

fun analyzeSpecification(lslFile: File): Library {
        val librarySpecification = LibSL(lslFile.path)
        return librarySpecification.loadFromFile(lslFile)
}

fun generateLibrary(library: Library) {
        val contents = StringBuilder().run {
                append("import ${library.metadata.name}.*;\n")

                for (inc in library.includes) {
                        append("import $inc;\n\n")
                }

                append("public class " + library.metadata.name + " {\n\n")

                generateClasses(library, library.automata, StringBuilder())
                toString()
        }

        print(library);
        File("src/main/java/okhttp3.java").writeText(contents)
}

fun generateClasses(library:Library, automatonList:List<Automaton>, sB: StringBuilder) {
        for (automaton in automatonList) {
                sB.append("\tpublic static class ${automaton.type.name} { \n\n")

                for (v in automaton.variables) {
                        sB.append("public final ${v.name};\n")
                }

                sB.append("\t${library.metadata.name}.${automaton.type.name} internal;\n\n")

                sB.append("\t${automaton.type.name}(${library.metadata.name}.${automaton.type.name} internal { this.internal = internal; }")

                generateFunctions(automaton.functions, StringBuilder())
        }

        sB.append("\n}")
}

fun generateFunctions(functionList:List<Function>, sB: StringBuilder) {
        for (function in functionList) {
                sB.append("\t\tpublic static " + ("" + ((getFunctionName(function)) ?: "void")))
                sB.append(" ")

                generateArgs(function, sB);
                generateBody(function, sB);
        }
        sB.append("\t} \n\n");
}

fun generateArgs(function:Function, sB:StringBuilder ) {

        sB.append(function.name + "(")
        for (arg in function.args) {
                sB.append(arg.type.name + " ")
                if (arg.annotation != null) {
                        sB.append("/*${arg.annotation!!.name}*/" + " ")
                }
                sB.append(arg.name)

                if (function.args.size > 1 && arg.index + 1 != function.args.size) {
                        sB.append(", ")
                }
        }

        sB.append(")")
}

fun generateBody(function:Function, sB:StringBuilder) {
        sB.append(" { \n")

        if(function.returnType != null){
                sB.append("\t\t${function.returnType} r = internal.${function.name}\n")
                sB.append("\t\treturn r")
        } else {
                sB.append("\t\tinternal.${function.name} \n")
                sB.append("\t\treturn this")
        }

        sB.append("\n\t\t}\n\n")
}

fun getFunctionName(function: Function): String? {
        return function.returnType?.name
}

