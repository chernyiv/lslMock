import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.asg.Automaton
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.Library
import java.io.File
import kotlin.text.StringBuilder

fun main(args: Array<String>) {

        val lslFile = File("src/test/resources/SimpleRandomization.lsl")
        val library = analyzeSpecification(lslFile)

        generateLibrary(library, lslFile)
}

fun analyzeSpecification(lslFile: File): Library {
        val librarySpecification = LibSL(lslFile.path)
        return librarySpecification.loadFromFile(lslFile)
}

fun generateLibrary(library: Library, lslFile: File) {
        val sb = StringBuilder()
        val contents = sb.run {
                //append("import ${library.metadata.name}.*;\n")
                append("import org.jeasy.random.EasyRandom;\nimport org.jeasy.random.EasyRandomParameters;\n\n")
                for (inc in library.includes) {
                        append("import $inc;\n\n")
                }

                append("public class new${library.metadata.name} {\n\n")

                append("\tstatic EasyRandom g = new EasyRandom();\n\n")

                generateClasses(library, library.automata, sb)
                toString()
        }

        print(library);
        File("src/main/java/new${lslFile.nameWithoutExtension}.java").writeText(contents)
}

fun generateClasses(library:Library, automatonList:List<Automaton>, sb: StringBuilder) {
        for (automaton in automatonList) {
                sb.append("\tpublic static class ${automaton.type.name} { \n\n")

                for (v in automaton.variables) {
                        sb.append("public final ${v.name};\n")
                }

                sb.append("\t\tstatic ${library.metadata.name}.${automaton.type.name} internal;\n\n")

                sb.append("\t\t${automaton.type.name}(${library.metadata.name}.${automaton.type.name} internal) { this.internal = internal; }\n\n")

                generateFunctions(library, automaton.functions, sb)
        }

        sb.append("\n}")
}

fun generateFunctions(library: Library, functionList: List<Function>, sb: StringBuilder) {
        for (function in functionList) {

                //FIX_THIS!!!!

                if (function.returnType!!.name != "String") {
                        sb.append("\t\tpublic static " + ("" + ((getFunctionName(function)) ?: "void")))
                } else {
                        sb.append("\t\tpublic " + ("" + ((getFunctionName(function)) ?: "void")))
                }
                sb.append(" ")

                generateArgs(function, sb);
                generateBody(library, function, sb);
        }
        sb.append("\t} \n\n");
}

fun generateArgs(function:Function, sb:StringBuilder ) {

        sb.append(function.name + "(")
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
        sb.append(" { \n")

        if(function.returnType != null){

                when(function.returnType!!.name) {
                        "String", "Integer", "char", "byte[]", "byte", "int", "short",
                        "long", "float", "double", "boolean" ->
                                sb.append("\t\t\t${function.returnType!!.name} r = internal.${function.name}();\n\t\t\treturn r;")

                        "Data" -> sb.append("\t\t\t${library.metadata.name}.${function.returnType!!.name} r = internal.${function.name}();\n" +
                                "\t\t\tr = g.nextObject(${library.metadata.name}.${function.returnType!!.name}.class);\n" +
                                "\t\t\treturn new ${function.returnType!!.name}(r);")

                        else -> sb.append("\t\t\t${library.metadata.name}.${function.returnType!!.name} r = internal.${function.name}();\n" +
                                "\t\t\treturn new ${function.returnType!!.name}(r);")
                }

        } else {
                sb.append("\t\tinternal.${function.name}();\n")
                sb.append("\t\treturn this;")
        }

        sb.append("\n\t\t}\n\n")
}

fun getFunctionName(function: Function): String? {
        return function.returnType?.name
}

