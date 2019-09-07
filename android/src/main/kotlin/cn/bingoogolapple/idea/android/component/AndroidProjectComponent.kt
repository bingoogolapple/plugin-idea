package cn.bingoogolapple.idea.android.component

import cn.bingoogolapple.idea.android.util.Logger
import cn.bingoogolapple.idea.android.util.Util
import com.intellij.openapi.components.ProjectComponent

class AndroidProjectComponent : ProjectComponent {
    override fun getComponentName(): String {
        return Util.getIdentity(this)
    }

    override fun initComponent() {
        Logger.debug("AndroidProjectComponent initComponent")
    }

    override fun projectOpened() {
        Logger.debug("AndroidProjectComponent projectOpened")
    }

    override fun projectClosed() {
        Logger.debug("AndroidProjectComponent projectClosed")
    }

    override fun disposeComponent() {
        Logger.debug("AndroidProjectComponent disposeComponent")
    }
}
