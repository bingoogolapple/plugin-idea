package cn.bingoogolapple.idea.android.action.common

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import javax.swing.Icon

abstract class BaseAction : AnAction {

    constructor() {}

    constructor(icon: Icon) : super(icon) {}

    constructor(text: String) : super(text) {}

    override fun actionPerformed(e: AnActionEvent) {
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
