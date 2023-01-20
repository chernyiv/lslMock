package library

import org.jetbrains.research.libsl.asg.Automaton
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.Library
import org.jetbrains.research.libsl.asg.Type

class LibraryObject(
    private val specification: Library
) {

    fun getLibName(): String {
        return specification.metadata.name
    }

    fun getLibClassList(): List<Automaton> {
        return specification.automata
    }

    fun getTypeMap(): Map<String, String> {
        return specification.semanticTypes.associateBy({t -> t.name}, {t -> t.fullName})
    }

    fun getImports(): MutableList<String> {
        return specification.imports
    }

    fun getIncludes(): MutableList<String> {
        return specification.includes
    }
}