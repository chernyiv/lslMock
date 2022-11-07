package generator

import com.squareup.javapoet.TypeSpec
import org.jetbrains.research.libsl.asg.Automaton
import javax.lang.model.element.Modifier

class ClassGenerator(val classes: List<Automaton>) {

    fun buildClasses(): List<TypeSpec> {
        return makeClassList()
    }

    private fun makeClassList(): List<TypeSpec> {
        val classList: MutableList<TypeSpec> = mutableListOf()
        for(c in classes) {
            classList.add(buildClass(c))
        }
        return classList
    }

    private fun buildClass(c: Automaton): TypeSpec {
        val cb: TypeSpec.Builder = TypeSpec.classBuilder(c.name)
            return cb.apply {
                addModifiers(Modifier.PUBLIC)
                startFunctionGenerator(c)
            }.build()
    }

    private fun startFunctionGenerator(c: Automaton) {
        val functionGenerator: FunctionGenerator = FunctionGenerator(c.functions)
        functionGenerator.buildFunctions()
    }
}