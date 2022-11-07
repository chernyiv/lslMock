package generator

import com.squareup.javapoet.MethodSpec
import org.jetbrains.research.libsl.asg.Function
import org.jetbrains.research.libsl.asg.FunctionArgument
import java.lang.reflect.Type
import javax.lang.model.element.Modifier

class FunctionGenerator(val functions: List<Function>) {

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
            } else {
                //returns(getClassType(f.returnType!!.name))
                returns(String.Companion::class.java)
            }
            if(f.args.isNotEmpty()) getParameters(f, mb)

        }.build()
    }

   /* private fun getFullType(type: String?): Type {
        return Class.forName(type).Companion::class.java
    }

    private fun returnCompanion(c: Class<Any>): Type {
        return c.Companion
    } */

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

    private fun getAnnotation(f: Function, mb: MethodSpec.Builder) {
        // TODO
        // mb.addAnnotation(f.typeAnnotation)
    }
}