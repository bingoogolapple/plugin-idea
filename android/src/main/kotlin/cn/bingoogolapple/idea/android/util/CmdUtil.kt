package cn.bingoogolapple.idea.android.util

import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import java.nio.charset.Charset
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
        Logger.info("$result | $successMsg | $errorMsg")
    }
}

fun String.runCmd(): CmdResult {
    println("执行命令：$this")
    Logger.info("\n执行命令：$this")

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

@Throws(ExecutionException::class)
fun String.runOnProcess(project: Project) {
    println("执行命令：$this")
    Logger.info("\n执行命令：$this")

    val generalCommandLine = GeneralCommandLine(this)
    generalCommandLine.charset = Charset.forName("UTF-8")
    generalCommandLine.setWorkDirectory(project.basePath)

    val processHandler = OSProcessHandler(generalCommandLine)
    processHandler.addProcessListener(object : ProcessListener {
        override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
            Logger.info(event.text)
        }

        override fun processTerminated(event: ProcessEvent) {
            Logger.info(event.text)
        }

        override fun processWillTerminate(event: ProcessEvent, willBeDestroyed: Boolean) {
            Logger.info(event.text)
        }

        override fun startNotified(event: ProcessEvent) {
            Logger.info(event.text)
        }
    })
    processHandler.startNotify()
}