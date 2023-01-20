package generator

import com.squareup.javapoet.*
import library.LibraryObject
import org.jetbrains.research.libsl.asg.Automaton
import javax.lang.model.element.Modifier

class ClassGenerator(val libraryObject: LibraryObject, val classes: List<Automaton>) {

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
                addMethod(buildConstructorForClass(c))
                addMethods(startFunctionGenerator(c))
                addField(TypeVariableName.get("${libraryObject.getLibName()}.${c.name}"), "internal")
            }.build()
    }

    private fun buildConstructorForClass(c: Automaton): MethodSpec {
        val cb = MethodSpec.constructorBuilder()
        return cb.apply {
            addModifiers(Modifier.PUBLIC)
            val originalClassName = ClassName.get("", libraryObject.getLibName())
            addParameter(originalClassName, "internal")
            addStatement("this.internal = internal")
        }.build()
    }

    private fun startFunctionGenerator(c: Automaton): List<MethodSpec> {
        val functionGenerator = FunctionGenerator(c.functions)
        return functionGenerator.buildFunctions()
    }
}