package cn.bingoogolapple.idea.android

import cn.bingoogolapple.idea.android.util.runCmd
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class BGAAndroidAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        Messages.showMessageDialog(project, "adb devices".runCmd().successMsg, "BGAAndroidAction", Messages.getInformationIcon())
    }
}