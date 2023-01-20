package project

import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import java.util.stream.Collectors
import java.util.zip.ZipEntry
import java.util.zip.ZipFile


class JarHandler {

    @Throws(IOException::class)
    fun extractArchive(archiveFile: Path, destPath: Path) {
        if(Files.exists(destPath)) {
            println("Jar already extracted")
            return
        } else {
            Files.createDirectories(destPath) // create dest path folder(s)
            ZipFile(archiveFile.toFile()).use { archive ->

                // sort entries by name to always create folders first
                val entries: List<ZipEntry> = archive.stream()
                    .sorted(Comparator.comparing(ZipEntry::getName))
                    .collect(Collectors.toList())

                // copy each entry in the dest path
                for (entry in entries) {
                    val entryDest: Path = destPath.resolve(entry.getName())
                    if (entry.isDirectory()) {
                        Files.createDirectory(entryDest)
                        continue
                    }
                    Files.copy(archive.getInputStream(entry), entryDest)
                }
            }
            Files.deleteIfExists(Path.of("$destPath\\META-INF\\MANIFEST.MF"))
            Files.deleteIfExists(Path.of("$destPath\\META-INF"))
        }
    }

    @Throws(IOException::class)
    fun run(inputPath: String) {
        val manifest = Manifest()
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0")
        val target = JarOutputStream(FileOutputStream("output.jar"), manifest)
        val inputDirectory = File(inputPath)
        for (nestedFile in inputDirectory.listFiles()!!) add("", nestedFile, target)
        target.close()
    }

    @Throws(IOException::class)
    private fun add(parents: String, source: File, target: JarOutputStream) {
        var `in`: BufferedInputStream? = null
        try {
            var name = (parents + source.getName()).replace("\\", "/")
            if (source.isDirectory()) {
                if (!name.isEmpty()) {
                    if (!name.endsWith("/")) name += "/"
                    val entry = JarEntry(name)
                    entry.setTime(source.lastModified())
                    target.putNextEntry(entry)
                    target.closeEntry()
                }
                for (nestedFile in source.listFiles()) add(name, nestedFile, target)
                return
            }
            val entry = JarEntry(name)
            entry.setTime(source.lastModified())
            target.putNextEntry(entry)
            `in` = BufferedInputStream(FileInputStream(source))
            val buffer = ByteArray(1024)
            while (true) {
                val count = `in`.read(buffer)
                if (count == -1) break
                target.write(buffer, 0, count)
            }
            target.closeEntry()
        } finally {
            `in`?.close()
        }
    }

    /* fun replaceJar(jarPath: String, byteCode: ByteArray, name: String) {
        val jarFile = File(jarPath)
        val tempJar = File("$jarPath.tmp")
        val jar = JarFile(jarFile)
        val jarUpdated = false

        val tempJarStream = JarOutputStream(FileOutputStream(tempJar))

        val buffer = ByteArray(1024)
        val entry = JarEntry(name)
        tempJarStream.putNextEntry(entry)
        tempJarStream.write(byteCode)

        var entryStream: InputStream
        for(e in jar.entries()) {
            if(!e.name.equals(name)) {
                entryStream = jar.getInputStream(e)
                tempJarStream.putNextEntry(e)

                while(entryStream.read(buffer) != 1) {
                    tempJarStream.write(buffer)
                }
                entryStream.close()
            }
        }
        tempJarStream.close()
        jar.close()
        tempJar.renameTo(File("New$jarFile"))
    } */
}

