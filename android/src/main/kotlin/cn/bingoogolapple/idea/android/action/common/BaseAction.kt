package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil
import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.ui.ConsoleView
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentManagerAdapter
import com.intellij.ui.content.ContentManagerEvent
import com.intellij.ui.content.impl.ContentImpl
import java.awt.BorderLayout
import java.awt.Component
import java.awt.LayoutManager
import java.nio.charset.Charset
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * http://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
 */
abstract class BaseAction : AnAction {
    lateinit var event: AnActionEvent

    constructor() {}

    constructor(icon: Icon) : super(icon) {}

    constructor(text: String) : super(text) {}

    override fun actionPerformed(e: AnActionEvent) {
        event = e
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

    protected fun processCommandline(tabTitle: String, toolWindowId: String, commandLine: GeneralCommandLine) {
        try {
            commandLine.charset = Charset.forName("UTF-8")
            val processHandler = OSProcessHandler(commandLine)
            ProcessTerminatedListener.attach(processHandler)

            ApplicationManager.getApplication().invokeLater { processConsole(tabTitle, toolWindowId, processHandler) }
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }

    private fun processConsole(tabTitle: String, toolWindowId: String, processHandler: ProcessHandler) {
        val toolWindow: ToolWindow = createToolWindow(toolWindowId)

        var tabContent: Content? = toolWindow.contentManager.findContent(tabTitle)
        if (tabContent == null) {
            tabContent = createTabContent(tabTitle, processHandler)
            toolWindow.contentManager.addContent(tabContent)
        } else {
            // tabContent.component 为 panel
            tabContent.component?.components?.forEach {
                if (it is ConsoleView) {
                    it.clear()
                    it.attachToProcess(processHandler)
                    processHandler.startNotify()
                }
                if (it is ActionToolbar) {
                    it.actions.forEach { action ->
                        if (action is StopProcessAction) {
                            action.stopProcess()
                            action.setProcessHandler(processHandler)
                        }
                    }
                }
            }
        }
        toolWindow.contentManager.setSelectedContent(tabContent)
        toolWindow.show(null)
    }

    private fun createTabContent(panelTitle: String, processHandler: ProcessHandler): Content {
        val consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(event.project!!).console
        consoleView.attachToProcess(processHandler)
        processHandler.startNotify()

        val tabPanel = JPanel(BorderLayout() as LayoutManager)
        tabPanel.add(consoleView.component as Component, BorderLayout.CENTER)

        val toolbarActions = DefaultActionGroup()
        val consoleActions = consoleView.createConsoleActions()
        toolbarActions.addAll(*consoleActions.copyOf(consoleActions.size))
        toolbarActions.add(StopProcessAction("停止", processHandler))

        val toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, toolbarActions as ActionGroup, false)
        toolbar.setTargetComponent(consoleView.component)
        tabPanel.add(toolbar.component as Component, BorderLayout.WEST)

        return ContentImpl(tabPanel, panelTitle, false)
    }

    private fun createToolWindow(toolWindowId: String): ToolWindow {
        val toolWindowManager = ToolWindowManager.getInstance(event.project!!)
        var toolWindow: ToolWindow? = toolWindowManager.getToolWindow(toolWindowId)

        if (toolWindow == null) {
            toolWindow = toolWindowManager.registerToolWindow(toolWindowId, true, ToolWindowAnchor.BOTTOM)
            toolWindow.icon = ImageIcon(javaClass.getResource("/icons/BGA.png"))
            toolWindow.title = toolWindowId
            toolWindow.stripeTitle = toolWindowId
            toolWindow.isShowStripeButton = true
            toolWindow.contentManager.addContentManagerListener(object : ContentManagerAdapter() {
                override fun contentRemoved(event: ContentManagerEvent) {
                    event.content.component?.components?.forEach {
                        if (it is ActionToolbar) {
                            it.actions.forEach { action ->
                                if (action is StopProcessAction) {
                                    action.stopProcess()
                                }
                            }
                        }
                    }
                }
            })
        }
        return toolWindow
    }
}
