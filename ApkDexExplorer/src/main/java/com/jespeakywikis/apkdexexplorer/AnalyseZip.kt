package com.jespeakywikis.apkdexexplorer

import java.io.File
import java.util.TreeSet
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class AnalyseZip(val apkPath: String, val type: String): ZipFile(apkPath){

    fun prepareZip(): List<File> {
        val tree = TreeSet<ZipEntry>()
        val entries = super.entries()
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            if (entry.name.endsWith(type)) {
                tree.add(entry)
            }
        }
        System.err.println(entries)
        return listOf(File(apkPath))
    }

    fun getBufferByteArray(): ByteArray? {
        val entryMap: HashMap<String, ZipEntry> = HashMap()
        super.entries().asSequence().forEach { entry ->
            if (entry.name.endsWith(".dex")) {
                entryMap[entry.name] = entry
                System.err.println(entry.name)
                getEntry(entry.name)?.let {
                    getInputStream(it).use { input ->
                        val bufferByteArray = input.readBytes()
                        if (bufferByteArray.isNotEmpty()) {
                            return bufferByteArray
                        }
                    }
                }
            }
        }
        return null
    }
}
