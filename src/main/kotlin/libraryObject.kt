import org.jetbrains.research.libsl.LibSL
import java.io.File
import kotlin.text.StringBuilder

fun main(args: Array<String>) {

        val lslFile = File("src/test/resources/okhttp3.lsl")
        val librarySpecification = LibSL("src/test/resources/okhttp3.lsl")
        val library = librarySpecification.loadFromFile(lslFile)

        val contents = StringBuilder().run {
                append("import ${library.metadata.name}.*;\n")

                for (inc in library.includes) {
                        append("import $inc;\n\n")
                }

                append("public class " + library.metadata.name + " {\n\n")

                for (automaton in library.automata) {
                        append("\tpublic static class ${automaton.type.name} { \n\n")

                        for (v in automaton.variables) {
                                append("public final ${v.name};\n")
                        }

                        append("\t\tstatic ${library.metadata.name}.${automaton.type.name} ${automaton.type.name.lowercase()}; \n\n ")

                        for (function in automaton.functions) {
                                append("\t\tpublic static " + ("" + ((function.returnType?.name) ?: "void")))
                                append(" ")
                                append(function.name + "(")

                                for (arg in function.args) {
                                        append(arg.type.name + " ")
                                        if (arg.annotation != null) {
                                                append("/*${arg.annotation!!.name}*/" + " ")
                                        }
                                        append(arg.name)

                                        if (function.args.size > 1 && arg.index + 1 != function.args.size) {
                                                append(", ")
                                        }
                                }

                                append(")")
                                append(" { \n")
                                append("\n\t\t}\n\n")
                        }
                        append("\t} \n\n");
                }

                append("\n}")
                toString()
        }

        print(library);
        File("src/main/java/okhttp3.java").writeText(contents)



}
