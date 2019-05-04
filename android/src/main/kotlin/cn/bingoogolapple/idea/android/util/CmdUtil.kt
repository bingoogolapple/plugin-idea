package cn.bingoogolapple.idea.android.util

import java.util.concurrent.TimeUnit

class CmdResult {
    var result: Int = 0
    var successMsg: String? = null
    var errorMsg: String? = null

    constructor(result: Int) {
        this.result = result
    }

    constructor(result: Int, successMsg: String?, errorMsg: String?) {
        this.result = result
        this.successMsg = successMsg
        this.errorMsg = errorMsg

        println("$result | $successMsg | $errorMsg")
    }
}

fun String.runCmd(): CmdResult {
    println("执行命令：$this")

    if (this.isEmpty()) {
        return CmdResult(-1)
    }

    var process: Process? = null
    try {
        process = Runtime.getRuntime().exec(this)
        if (!process!!.waitFor(15, TimeUnit.SECONDS)) {
            return CmdResult(-1, null, "执行 $this 超时")
        }

        return if (process.exitValue() == 0) {
            CmdResult(0, process.inputStream.bufferedReader().use { it.readText() }, null)
        } else {
            CmdResult(process.exitValue(), null, process.errorStream.bufferedReader().use { it.readText() })
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return CmdResult(-1, null, e.message)
    } finally {
        process?.destroy()
    }
}