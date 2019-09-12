package cn.bingoogolapple.idea.android.action.study

import cn.bingoogolapple.idea.android.action.common.BaseAction
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages

class TestDialog : BaseAction("测试对话框") {
    override fun onMainThread() {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val resultStr = Messages.showInputDialog(project,
                "我是 InputDialog 的 message",
                "我是 InputDialog 的 title",
                Messages.getQuestionIcon())
        Messages.showMessageDialog(project,
                resultStr,
                "我是 MessageDialog 的 title",
                Messages.getInformationIcon())
    }
}
