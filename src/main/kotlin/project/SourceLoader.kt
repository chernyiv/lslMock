package project

import java.io.File

class SourceLoader: IProjectLoader {
    override fun getProject(path: String) {
        listOfFiles(File(path))
    }

    private fun listOfFiles(dirPath: File) {
        val fileList = dirPath.listFiles()
        for(file in fileList) {
            if(file.isFile) {
                println("Project path: ${file.name}")
                println(" ")
                println("File extension: ${file.extension}")
                println("Absolute path: ${file.absolutePath}")
                println("Canonical path: ${file.canonicalPath}\n")
            } else {
                listOfFiles(file)
            }
        }
    }
}