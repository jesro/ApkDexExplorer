package com.jespeakywikis.apkdexexplorer

class DisassembleDex(apkPath: String, type: String) {
    private val analyseZip by lazy { AnalyseZip(apkPath, type).getBufferByteArray() }
    private val analyseDex by lazy { AnalyseDex(analyseZip).analyse() }

    fun getDex() {
        return analyseDex
    }
}

