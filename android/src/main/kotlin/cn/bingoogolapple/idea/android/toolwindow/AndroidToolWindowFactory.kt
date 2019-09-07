package cn.bingoogolapple.idea.android.toolwindow

import cn.bingoogolapple.idea.android.view.AndroidToolWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class AndroidToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val androidToolWindow = AndroidToolWindow(toolWindow)
        val content = ContentFactory.SERVICE.getInstance().createContent(androidToolWindow.contentView, "", false)
        toolWindow.contentManager.addContent(content)
    }
}