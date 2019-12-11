package cn.bingoogolapple.idea.android

import cn.bingoogolapple.idea.android.action.common.BaseAction
import com.intellij.execution.configurations.GeneralCommandLine

class BGAAndroidAction : BaseAction() {
    override fun onChildThread() {
        val commandLine = GeneralCommandLine()
        commandLine.setWorkDirectory(event.project!!.basePath)
        commandLine.exePath = "curl"
        commandLine.addParameter("-v")
        commandLine.addParameter("http://www.bingoogolapple.cn")
        processCommandline("测试curl", "BGA Console", commandLine)
    }
}