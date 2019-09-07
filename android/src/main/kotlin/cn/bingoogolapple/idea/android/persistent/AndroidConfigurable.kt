package cn.bingoogolapple.idea.android.persistent

import cn.bingoogolapple.idea.android.util.Util
import cn.bingoogolapple.idea.android.view.AndroidConfigForm
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class AndroidConfigurable : Configurable {
    private var configForm = AndroidConfigForm()
    private val persistent = ServiceManager.getService(AndroidPersistentStateComponent::class.java)

    override fun getDisplayName(): String {
        return Util.getIdentity("")
    }

    override fun createComponent(): JComponent? {
        return configForm.`$$$getRootComponent$$$`()
    }

    override fun isModified(): Boolean {
        return configForm.state != persistent.state
    }

    override fun apply() {
        persistent.loadState(configForm.state)
    }

    override fun reset() {
        configForm.reset(persistent.state)
    }
}
