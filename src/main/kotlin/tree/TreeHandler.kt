package tree

import org.jetbrains.research.libsl.asg.Library

interface TreeHandler {

    // Creating library object from the parsed spec
    // Создаем объект библиотеки из полученного дерева
    fun setLibrary(libName: String, libTree: Library)

        /* fun setLibrary(libName: String, libTree: Library) {
        val library = LibraryObject(
            "new${libName}",
            libTree.metadata.


        )
    } */
}