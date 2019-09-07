package cn.bingoogolapple.idea.android.component

import cn.bingoogolapple.idea.android.util.Logger
import com.intellij.openapi.components.ApplicationComponent

class AndroidApplicationComponent : ApplicationComponent {
    override fun getComponentName(): String {
        return "BGA-IDEA-Android"
    }

    override fun initComponent() {
        Logger.debug("AndroidApplicationComponent initComponent")
    }

    override fun disposeComponent() {
        Logger.debug("AndroidApplicationComponent disposeComponent")
    }
}
