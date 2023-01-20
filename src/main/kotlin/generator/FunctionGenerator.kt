package generator

import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeVariableName
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.FunctionArgument
import java.lang.reflect.Type
import javax.lang.model.element.Modifier

class FunctionGenerator(
    val functions: List<Function>
) {

    fun buildFunctions(): List<MethodSpec> {
        return makeFunctionList()
    }

    private fun makeFunctionList(): List<MethodSpec> {
        val funcList: MutableList<MethodSpec> = mutableListOf()
            for(f in functions) {
                funcList.add(buildFunction(f))
            }
        return funcList
    }

    private fun buildFunction(f: Function): MethodSpec {
        val mb: MethodSpec.Builder = MethodSpec.methodBuilder(f.name)
        return mb.apply {
            addModifiers(Modifier.PUBLIC)
            if(f.returnType == null) {
                returns(Void.TYPE)
                addStatement("internal.${f.name}();")
            } else {
                println(isJDKClass(f.returnType!!.name))
                returns(TypeVariableName.get(f.returnType!!.name))
                addStatement("${f.returnType!!.name} r = internal.${f.name}()")
                addStatement("return new ${f.returnType!!.name}(r)")
            }
            if(f.args.isNotEmpty()) getParameters(f, mb)


        }.build()
    }

    private fun getParameters(f: Function,
        mb: MethodSpec.Builder
    ) {
        for(p in f.args) {
            mb.addParameter(getParamType(p), p.name)
        }
    }

    private fun getParamType(p: FunctionArgument): Type {
        return Class.forName(p.type.name)
    }

    private fun isJDKClass(t: String): Boolean {
        println(ClassLoader.getSystemClassLoader().definedPackages)
        //println(Class.forName(t.javaClass.toString()).`package`)
        println(t.javaClass.`package`.name)
        return t.javaClass.getPackage().name.startsWith("java")
    }
}