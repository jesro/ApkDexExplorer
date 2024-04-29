package com.jespeakywikis.apkdexexplorer

import java.nio.charset.Charset

class AnalyseDex(private val bufferByteArray: ByteArray?) {

    fun analyse() {
        System.err.println(bufferByteArray?.toString(Charset.forName("UTF-8")))
    }
}