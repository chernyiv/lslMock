package fuzzer

class Runner(val projectPath: String, val testClass: String) {

    fun runCommand() {
        val p = ProcessBuilder("java", "-jar",
            "D:/UltimateIDEA_Projects/java-fuzzing-example/lib/jqf-fuzz-2.0-SNAPSHOT-zest-cli.jar",
            "-t=10",
            projectPath,
            testClass,
            "fuzz"
        ).inheritIO().start()
        val status = p.waitFor()
        println("Exited with status: $status")
    }
}