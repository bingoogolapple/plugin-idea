package cn.bingoogolapple.idea.android.persistent

import cn.bingoogolapple.idea.android.util.Util
import cn.bingoogolapple.idea.android.view.AndroidConfigForm
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class AndroidConfigurable : Configurable {
    private var configForm = AndroidConfigForm()
    private val persistent = ServiceManager.getService(AndroidPersistentStateComponent::class.java)

    // 控制在配置面板中左侧窗口的显示名称
    override fun getDisplayName(): String {
        return Util.getIdentity("")
    }

    // 配置界面的绘制，负责用户输入信息的接受
    override fun createComponent(): JComponent? {
        return configForm.contentView
    }

    // 当用户修改配置参数后，在点击 OK、Apply 按钮前，框架会自动调用该方法，判断是否有修改，进而控制 OK、Apply 按钮是否可用
    override fun isModified(): Boolean {
        return configForm.state != persistent.state
    }

    // 用户点击 OK、Apply 按钮后会调用该方法
    override fun apply() {
        persistent.loadState(configForm.state)
    }

    // 用户点击 Reset 按钮后会调用该方法，用户界面配置参数重置
    override fun reset() {
        configForm.reset(persistent.state)
    }
}
