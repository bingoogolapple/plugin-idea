package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import javax.swing.Icon

abstract class BaseAction : AnAction {

    constructor() {}

    constructor(icon: Icon) : super(icon) {}

    constructor(text: String) : super(text) {}

    override fun actionPerformed(e: AnActionEvent) {
        AdbUtil.updateAdbPath(e.project!!)

        onMainThread()
        Thread {
            onChildThread()
        }.start()
    }

    open fun onMainThread() {
    }

    open fun onChildThread() {
    }
}
