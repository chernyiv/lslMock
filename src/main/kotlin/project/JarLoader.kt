package project

import com.google.common.reflect.ClassPath
import javassist.ClassPool
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class JarLoader: IProjectLoader {
    override fun getProject(path: String) {
        val classPool = ClassPool.getDefault()
        classPool.insertClassPath(path)
        val classToChange = classPool.getCtClass("org.example.GetRequest")
        val reflections = Reflections( classToChange.packageName, Scanners.SubTypes.filterResultsBy{ s -> true})

        println(reflections)
        val classes = reflections.getSubTypesOf(Any::class.java)
        println(classes.size)
        for(c in classes){
            println(c.declaredClasses)
        }

        val cp: ClassPath = ClassPath.from(classPool.classLoader)
        for (info in cp.topLevelClasses) {
            if(info.packageName == "org/example/") {
                println("Guava check start")
                //println(info.packageName)
                println(info.name)
                println(info.simpleName)
                println(info.url())
                println(info.load().canonicalName)
                println("Guava check finish")
            }
        }


        /*val classNames: MutableList<String> = ArrayList()
        val zip = ZipInputStream(FileInputStream("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\exampleForLsl-1.0-SNAPSHOT.jar"))
            var entry: ZipEntry = zip.getNextEntry()
            while (entry != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    val className: String = entry.getName().replace('/', '.') // including ".class"
                    classNames.add(className.substring(0, className.length - ".class".length))
                    println(className)
                }
                entry = zip.getNextEntry()
            }
        println(zip)

         */
    }


    private fun editCode(path: String) {

        println("Starting javaassist")
        /* val jarFile = JarFile(path)
        val pathInsideJar = "/GetRequest.class"
        for(e in jarFile.entries()){
            println(e.name)
            if(e.name.endsWith(".class")) {
                println(e.method)
            }
        } */

        println(path)
        val pool = ClassPool.getDefault()
        pool.insertClassPath(path)
        val cc = pool.get("org.example.GetRequest")
        println(cc)
        cc.getDeclaredMethod("sendGetRequest").setBody("{System.out.println(\"Hello, World!\"); \n return 0;}")
        cc.writeFile("D:\\UltimateIDEA_Projects\\exampleForLsl\\build\\libs\\rebuilt")

        // pool.removeClassPath(File(path)) // need to remove the classpath to release connection to JAR file so we can update it.

        // jarHandler.replaceJar(path, b, "org/example/GetRequest")

        /*
        val zipEntry: ZipEntry = jarFile.getEntry(pathInsideJar)
        val fis: InputStream = jarFile.getInputStream(zipEntry)

        val pool = ClassPool.getDefault()
        val cc: CtClass = pool.makeClass(fis)
        fis.close()
        jarFile.close()


        val classFileName: String =
            pathInsideJar.replace("\\", "/").substring(0, pathInsideJar.lastIndexOf('/'))
        val out = DataOutputStream(FileOutputStream(classFileName))
        cc.getClassFile().write(out)

        val launchenv: MutableMap<String, String> = HashMap()
        val launchuri: URI = URI.create("jar:" + File(path).toURI())
        launchenv["create"] = "true"
        FileSystems.newFileSystem(launchuri, launchenv).use { zipfs ->
            val externalClassFile: Path = Paths.get(classFileName)
            val pathInJarfile: Path = zipfs.getPath(pathInsideJar)
            // copy a file into the zip file
            Files.copy(
                externalClassFile, pathInJarfile,
                StandardCopyOption.REPLACE_EXISTING
            )
        }

         */

        println("javassist finished")
    }
}