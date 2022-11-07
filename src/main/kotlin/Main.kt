import java.io.File


fun main(args: Array<String>) {

    val specFile = File("src/test/resources/SimpleRandomization.lsl")
    val specHandler = SpecificationHandler(specFile)
    specHandler.startHandler()
}