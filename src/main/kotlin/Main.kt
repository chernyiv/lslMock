import fuzzer.Runner
import library.LibraryObject
import project.JarHandler
import project.JarLoader
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URLClassLoader
import java.nio.file.Path


/* fun main(args: Array<String>) {

    //val projectPath = File(args[0]).toURI().toURL()
    //val project = URLClassLoader.newInstance(projectPath)
    val specFile = File("src/test/resources/SimpleRandomization.lsl")
    val specHandler = SpecificationHandler(specFile)
    specHandler.startHandler()
    JarHandler().extractArchive(Path.of("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\exampleForLsl-1.0-SNAPSHOT.jar"),
        Path.of("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\extracted"))

    JarLoader().getProject("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\extracted")

    JarHandler().run("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\extracted\\")

    Runner(
        "D:/UltimateIDEA_Projects/java-fuzzing-example/target/example-java-1.0-SNAPSHOT-fat-tests.jar",
        "dev.fuzzit.examplejava.OkhttpUsageTest"
    ).runCommand()
} */
