package cn.bingoogolapple.idea.android.action.common

import com.intellij.execution.impl.ExecutionManagerImpl
import com.intellij.execution.process.ProcessHandler
import com.intellij.openapi.actionSystem.AnActionEvent

class StopProcessAction : com.intellij.execution.actions.StopProcessAction {
    private var myProcessHandler: ProcessHandler? = null

    constructor(text: String, processHandler: ProcessHandler?) : super(text, text, processHandler) {
        myProcessHandler = processHandler
    }

    override fun setProcessHandler(processHandler: ProcessHandler?) {
        myProcessHandler = processHandler
    }

    override fun update(e: AnActionEvent) {
        update(e.presentation, templatePresentation, myProcessHandler)
    }

    override fun actionPerformed(e: AnActionEvent) {
        stopProcess()
    }

    fun stopProcess() {
        ExecutionManagerImpl.stopProcess(myProcessHandler)
    }
}
