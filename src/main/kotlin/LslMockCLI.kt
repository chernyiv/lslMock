import fuzzer.Runner
import picocli.CommandLine
import picocli.CommandLine.Parameters
import project.JarHandler
import project.JarLoader
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.system.exitProcess

@CommandLine.Command(name = "LslMock")
class LslMockCLI: Runnable {

    @Parameters(index = "0", paramLabel = "PROJECT_PATH", description = ["Path to the project"])
    private var projectPath: String = ""

    @Parameters(index = "1", paramLabel = "SPECIFICATION_PATH", description = ["Path to the lsl spec"])
    private var specificationPath: String = ""

    override fun run() {
        val specFile = File(specificationPath)
        val specHandler = SpecificationHandler(specFile)
        val extractedDir = "${Paths.get(projectPath).parent}\\extracted"
        specHandler.startHandler()
        JarHandler().extractArchive(
            Path.of(projectPath),
            Path.of(extractedDir))

        JarLoader().getProject(extractedDir)

        JarHandler().run(extractedDir)

        Runner(
            "D:/UltimateIDEA_Projects/java-fuzzing-example/target/example-java-1.0-SNAPSHOT-fat-tests.jar",
            "dev.fuzzit.examplejava.OkhttpUsageTest"
        ).runCommand()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            exitProcess(CommandLine(LslMockCLI()).execute(*args))
        }
    }
}